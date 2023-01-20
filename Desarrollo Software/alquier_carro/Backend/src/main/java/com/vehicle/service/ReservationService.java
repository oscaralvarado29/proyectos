package com.vehicle.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.vehicle.dto.ReservationRequest;
import com.vehicle.dto.ReservationResponse;
import com.vehicle.dto.ReservationUpdate;
import com.vehicle.exception.DatesNotValidException;
import com.vehicle.exception.ReservationNotFoundException;
import com.vehicle.exception.VehicleIsReservedException;
import com.vehicle.mapper.ReservationMapper;
import com.vehicle.model.Client;
import com.vehicle.model.Reservation;
import com.vehicle.model.Vehicle;
import com.vehicle.report.ClientCount;
import com.vehicle.report.ReservationStatus;
import com.vehicle.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Oscar Alvarado
 */
@RequiredArgsConstructor
@Service
@Transactional
public class ReservationService implements IReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    /**
     * @return all reservations from database
     */
    @Override
    public List<ReservationResponse> getAllReservation() {
        return reservationMapper.toReservationResponseList(reservationRepository.findAll());
    }

    /**
     * @param reservationId id of reservation to search
     * @return reservation with id equals to reservationId
     */
    @Override
    public ReservationResponse getReservation(int reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(ReservationNotFoundException::new);
        return reservationMapper.toReservationResponse(reservation);
    }

    /**
     * @param reservationRequest reservation to save
     */
    @Override
    public void saveReservation(ReservationRequest reservationRequest) {
        Optional<Reservation> reservationInDB = reservationRepository.findByStartDateAndDevolutionDateAndVehicle(reservationRequest.getStartDate(), reservationRequest.getDevolutionDate(), reservationRequest.getVehicle());
        while (reservationInDB.isPresent()) {
            if ((reservationRequest.getStartDate().compareTo(reservationInDB.get().getStartDate()) >= 0 && reservationRequest.getStartDate().compareTo(reservationInDB.get().getDevolutionDate()) <= 0) || (reservationRequest.getDevolutionDate().compareTo(reservationInDB.get().getStartDate()) >= 0 && reservationRequest.getDevolutionDate().compareTo(reservationInDB.get().getDevolutionDate()) <= 0)) {
                throw new VehicleIsReservedException();
            }
        }
        reservationRepository.save(reservationMapper.toReservation(reservationRequest));
    }

    /**
     * @param reservationId id of reservation to delete
     */
    @Override
    public void deleteReservation(int reservationId) {
        if (!reservationRepository.existsById(reservationId)) {
            throw new ReservationNotFoundException();
        }
        reservationRepository.deleteById(reservationId);
    }

    /**
     * @param reservationUpdate reservation to update
     */
    @Override
    public void updateReservation(ReservationUpdate reservationUpdate) {
        Reservation reservationInDB = reservationRepository.findById(reservationUpdate.getIdReservation()).orElseThrow(ReservationNotFoundException::new);
        if (reservationUpdate.getStartDate() != null) {
            reservationInDB.setStartDate(reservationUpdate.getStartDate());
        }
        if (reservationUpdate.getDevolutionDate() != null) {
            reservationInDB.setDevolutionDate(reservationUpdate.getDevolutionDate());
        }
        if (reservationUpdate.getStatus() != null) {
            reservationInDB.setStatus(reservationUpdate.getStatus());
        }
        reservationRepository.save(reservationInDB);
    }

    /**
     * @return report of reservations status
     */
    @Override
    public ReservationStatus getReservationsStatusReport() {
        List<Reservation>completed= reservationRepository.findAllByStatus("completed");
        List<Reservation>cancelled= reservationRepository.findAllByStatus("cancelled");
        return new ReservationStatus(completed.size(), cancelled.size());
    }

    /**
     * @param dateOne date to start reservation period report
     * @param dateTwo date to end reservation period report
     * @return report of reservations in period
     */
    @Override
    public List<ReservationResponse> getReservationPeriod(String dateOne, String dateTwo) throws ParseException {
        SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd");
        Date aDate = parser.parse(dateOne);
        Date bDate = parser.parse(dateTwo);
        if(aDate.after(bDate)){
            throw new DatesNotValidException();
        }
        return reservationMapper.toReservationResponseList(reservationRepository.findAllByStartDateAfterAndStartDateBefore(aDate, bDate));
    }

    /**
     * @return report of clients with yours reservations
     */
    @Override
    public List<ClientCount> getTopClients() {
        List<ClientCount> clientCounts= new ArrayList<>();
        List<Object[]> report = reservationRepository.countTotalReservationByClient();
        for (Object[] objects : report) {
            clientCounts.add(new ClientCount((Long) objects[1], (Client) objects[0]));
        }
        return clientCounts;
    }

    /**
     * @param client client to search
     * @param vehicle vehicle to search
     * @return report of reservations by client and vehicle
     */
    @Override
    public List<ReservationResponse> getReservationByClientAndVehicle(Client client, Vehicle vehicle) {
        return reservationMapper.toReservationResponseList(reservationRepository.findAllByClientAndVehicle(client, vehicle));
    }
}
