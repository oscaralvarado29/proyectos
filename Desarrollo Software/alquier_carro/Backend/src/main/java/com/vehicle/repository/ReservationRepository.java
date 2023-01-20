package com.vehicle.repository;

import com.vehicle.model.Client;
import com.vehicle.model.Reservation;
import com.vehicle.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

    Optional<Reservation> findByStartDateAndDevolutionDateAndVehicle(Date startDate, Date devolutionDate, Vehicle idVehicle);

    List<Reservation> findAllByStatus (String status);

    List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date dateOne, Date dateTwo);

    List<Reservation> findAllByClientAndVehicle(Client idClient, Vehicle idVehicle);

    @Query("SELECT c.client, COUNT(c.client) FROM Reservation AS c group by c.client order by COUNT(c.client)DESC")
    List<Object[]> countTotalReservationByClient();
}
