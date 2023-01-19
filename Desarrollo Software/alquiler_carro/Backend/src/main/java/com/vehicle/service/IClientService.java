package com.vehicle.service;

import com.vehicle.dto.ClientRequest;
import com.vehicle.dto.ClientResponse;
import com.vehicle.dto.ClientUpdate;

import java.util.List;

public interface IClientService {
    List<ClientResponse> getAllClients();
    ClientResponse getClient (int idClient);
    void saveClient(ClientRequest clientRequest);
    void deleteClient(int clientId);
    void updateClient(ClientUpdate clientUpdate);
}
