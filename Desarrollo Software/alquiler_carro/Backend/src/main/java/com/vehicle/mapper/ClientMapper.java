package com.vehicle.mapper;

import com.vehicle.dto.ClientRequest;
import com.vehicle.dto.ClientResponse;
import com.vehicle.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {
    Client toClient(ClientRequest clientRequest);
    ClientResponse toClientResponse(Client client);
    List<ClientResponse> toClientResponseList(List<Client> clientList);
}
