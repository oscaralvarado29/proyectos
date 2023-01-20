package com.vehicle.service;

import com.vehicle.dto.VehicleRequest;
import com.vehicle.dto.VehicleResponse;
import com.vehicle.dto.VehicleUpdate;

import java.util.List;

public interface IVehicleService {
    List<VehicleResponse> getAllVehicles();
    VehicleResponse getVehicle (int vehicleId);
    void saveVehicle(VehicleRequest vehicleRequest);
    void deleteVehicle(int vehicleId);
    void updateVehicle(VehicleUpdate vehicleUpdate);
}
