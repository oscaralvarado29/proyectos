package com.vehicle.service;

import com.vehicle.dto.ClientRequest;
import com.vehicle.dto.ClientResponse;
import com.vehicle.dto.ClientUpdate;
import com.vehicle.exception.ClientAlreadyExistsException;
import com.vehicle.exception.ClientNotFoundException;
import com.vehicle.factory.ClientDataTest;
import com.vehicle.mapper.ClientMapper;
import com.vehicle.model.Client;
import com.vehicle.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ClientServiceTest {
    @InjectMocks
    ClientService clientService;
    @Mock
    ClientRepository clientRepository;
    @Mock
    ClientMapper clientMapper;
    private final Client client = new Client();

    @BeforeEach
    void setUp() {
        client.setIdClient(1);
        client.setEmail("correo.hotmail.com");
        client.setName("Carlos");
        client.setAge(25);
    }

    @Test
    void mustGetAllClientsOfDB() {
        ClientResponse clientResponse = ClientDataTest.getClientResponse();

        when(clientRepository.findAll()).thenReturn(List.of(client));
        when(clientMapper.toClientResponseList(List.of(client))).thenReturn(List.of(clientResponse));

        clientService.getAllClients();
        verify(clientRepository).findAll();
    }

    @Test
    void mustGetSpecificClientByIdOfDB() {
        ClientResponse clientResponse = ClientDataTest.getClientResponse();

        when(clientRepository.findById(client.getIdClient())).thenReturn(Optional.of(client));
        when(clientMapper.toClientResponse(client)).thenReturn(clientResponse);

        clientService.getClient(client.getIdClient());
        verify(clientRepository).findById(client.getIdClient());
    }

    @Test
    void mustTrowClientNotFoundExceptionWhenTheClientCorrespondentToTheIdDoesNotExist(){
        when(clientRepository.findById(5)).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> clientService.getClient(5));
    }
    @Test
    void mustSaveClientInTheDB() {
        ClientRequest clientRequest = ClientDataTest.getClientRequest();

        when(clientRepository.existsByName(clientRequest.getName())).thenReturn(false);
        when(clientMapper.toClient(clientRequest)).thenReturn(client);

        clientService.saveClient(clientRequest);
        verify(clientRepository).save(client);
    }

    @Test
    void mustTrowClientAlreadyExistsExceptionWhenTheClientAlreadyExistsInTheDB() {
        ClientRequest clientRequest = ClientDataTest.getClientRequest();

        when(clientRepository.existsByName(clientRequest.getName())).thenReturn(true);

        assertThrows(ClientAlreadyExistsException.class, () -> clientService.saveClient(clientRequest));
    }
    @Test
    void deleteClient() {
        when(clientRepository.existsById(client.getIdClient())).thenReturn(true);

        clientService.deleteClient(client.getIdClient());
        verify(clientRepository).deleteById(client.getIdClient());
    }

    @Test
    void mustTrowClientNotFoundExceptionWhenTheClientCorrespondentToTheIdDoesNotExistToDelete() {
        when(clientRepository.existsById(5)).thenReturn(false);

        assertThrows(ClientNotFoundException.class, () -> clientService.deleteClient(5));
    }

    @Test
    void updateClient() {
        ClientUpdate clientUpdate = ClientDataTest.getClientUpdate();

        when(clientRepository.findById(clientUpdate.getIdClient())).thenReturn(Optional.of(client));

        clientService.updateClient(clientUpdate);
        verify(clientRepository).save(client);
    }

    @Test
    void mustTrowCategoryNotFoundExceptionWhenTheCategoryCorrespondentToTheIdDoesNotExistToUpdate() {
        ClientUpdate clientUpdate = ClientDataTest.getClientUpdate();

        when(clientRepository.findById(clientUpdate.getIdClient())).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> clientService.updateClient(clientUpdate));
    }
}