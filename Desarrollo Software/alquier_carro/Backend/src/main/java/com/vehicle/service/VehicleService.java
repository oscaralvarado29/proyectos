package com.vehicle.service;

import com.vehicle.dto.VehicleRequest;
import com.vehicle.dto.VehicleResponse;
import com.vehicle.dto.VehicleUpdate;
import com.vehicle.exception.MessageAlreadyExistsException;
import com.vehicle.exception.VehicleAlreadyExistsException;
import com.vehicle.exception.VehicleNotFoundException;
import com.vehicle.mapper.VehicleMapper;
import com.vehicle.model.Vehicle;
import com.vehicle.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Oscar Alvarado
 */
@RequiredArgsConstructor
@Service
@Transactional
public class VehicleService implements IVehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    /**
     * @return all vehicles from database
     */
    @Override
    public List<VehicleResponse> getAllVehicles() {
        return vehicleMapper.toVehicleResponseList(vehicleRepository.findAll());
    }

    /**
     * @param vehicleId id of vehicle to search
     * @return vehicle with id equals to vehicleId
     */
    @Override
    public VehicleResponse getVehicle(int vehicleId) {
        return vehicleMapper.toVehicleResponse(vehicleRepository.findById(vehicleId).orElseThrow(VehicleNotFoundException::new));
    }

    /**
     * @param vehicleRequest vehicle to save
     */
    @Override
    public void saveVehicle(VehicleRequest vehicleRequest) {
        Optional<Vehicle> vehicle = vehicleRepository.findVehicleByNameAndAndBrandAndCategory(vehicleRequest.getName(), vehicleRequest.getBrand(), vehicleRequest.getCategory());
        if (vehicle.isPresent()) {
            throw new VehicleAlreadyExistsException();
        }
        vehicleRepository.save(vehicleMapper.toVehicle(vehicleRequest));
    }

    /**
     * @param vehicleId id of vehicle to delete
     */
    @Override
    public void deleteVehicle(int vehicleId) {
        if (!vehicleRepository.existsById(vehicleId)) {
            throw new VehicleNotFoundException();
        }
        vehicleRepository.deleteById(vehicleId);
    }

    /**
     * @param vehicleUpdate vehicle to update
     */
    @Override
    public void updateVehicle(VehicleUpdate vehicleUpdate) {
        Vehicle vehicle = vehicleRepository.findById(vehicleUpdate.getIdVehicle()).orElseThrow(VehicleNotFoundException::new);
        if (vehicleUpdate.getName() != null) {
            vehicle.setName(vehicleUpdate.getName());
        }
        if (vehicleUpdate.getColor() != null) {
            vehicle.setColor(vehicleUpdate.getColor());
        }
        if (vehicleUpdate.getBrand() != null) {
            vehicle.setBrand(vehicleUpdate.getBrand());
        }
        if (vehicleUpdate.getModel() != null) {
            vehicle.setModel(vehicleUpdate.getModel());
        }
        if (vehicleUpdate.getHorsePower() != null) {
            vehicle.setHorsePower(vehicleUpdate.getHorsePower());
        }
        if (vehicleUpdate.getEngineCylinders() != null) {
            vehicle.setEngineCylinders(vehicleUpdate.getEngineCylinders());
        }
        if (vehicleUpdate.getSeating() != null) {
            vehicle.setSeating(vehicleUpdate.getSeating());
        }
        vehicleRepository.save(vehicle);
    }
}
