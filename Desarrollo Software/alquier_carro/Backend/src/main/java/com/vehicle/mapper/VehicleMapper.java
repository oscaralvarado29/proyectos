package com.vehicle.mapper;

import com.vehicle.dto.VehicleRequest;
import com.vehicle.dto.VehicleResponse;
import com.vehicle.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface VehicleMapper {
    Vehicle toVehicle(VehicleRequest vehicleRequest);
    List<VehicleResponse> toVehicleResponseList(List<Vehicle> vehicleList);
    VehicleResponse toVehicleResponse(Vehicle vehicle);
}
