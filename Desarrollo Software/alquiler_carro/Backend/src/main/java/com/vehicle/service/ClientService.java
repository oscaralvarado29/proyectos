package com.vehicle.service;

import java.util.List;
import com.vehicle.dto.ClientRequest;
import com.vehicle.dto.ClientResponse;
import com.vehicle.dto.ClientUpdate;
import com.vehicle.exception.ClientAlreadyExistsException;
import com.vehicle.exception.ClientNotFoundException;
import com.vehicle.mapper.ClientMapper;
import com.vehicle.model.Client;
import com.vehicle.repository.ClientRepository;
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
public class ClientService implements IClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;


    /**
     * @return all client in database
     */
    @Override
    public List<ClientResponse> getAllClients() {
        return clientMapper.toClientResponseList(clientRepository.findAll());
    }

    /**
     * @param idClient id of client to search
     * @return client with id equals to idClient
     */
    @Override
    public ClientResponse getClient(int idClient) {
        Client client = clientRepository.findById(idClient).orElseThrow(ClientNotFoundException::new);
        return clientMapper.toClientResponse(client);
    }

    /**
     * @param clientRequest client to save
     */
    @Override
    public void saveClient(ClientRequest clientRequest) {
        if (clientRepository.existsByName(clientRequest.getName())) {
            throw new ClientAlreadyExistsException();
        }
        clientRepository.save(clientMapper.toClient(clientRequest));
    }

    /**
     * @param clientId id of client to delete
     */
    @Override
    public void deleteClient(int clientId) {
        if (!clientRepository.existsById(clientId)) {
            throw new ClientNotFoundException();
        }
        clientRepository.deleteById(clientId);
    }

    /**
     * @param clientUpdate client to update
     */
    @Override
    public void updateClient(ClientUpdate clientUpdate) {
        Client clientInDB = clientRepository.findById(clientUpdate.getIdClient()).orElseThrow(ClientNotFoundException::new);
        if (clientUpdate.getName() != null) {
            clientInDB.setName(clientUpdate.getName());
        }
        if (clientUpdate.getEmail() != null) {
            clientInDB.setEmail(clientUpdate.getEmail());
        }
        if (clientUpdate.getAge() != null) {
            clientInDB.setAge(clientUpdate.getAge());
        }
        clientRepository.save(clientInDB);
    }
}
