package com.vehicle.service;

import java.util.List;
import java.util.Optional;

import com.vehicle.model.Client;
import com.vehicle.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oscar Alvarado
 */
@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    /**
     * GET
     * @return the call of the getAll method of the class ClientRepository
     */
    public List<Client> getAll(){
        return clientRepository.getAll();
    }

    /**
     * GET select a specific id
     * @param clientId client id to get
     * @return the call of the getClient method of the class ClientRepository
     */
    public Optional<Client> getClient(int clientId) {
        return clientRepository.getClient(clientId);
    }

    /**
     * POST
     * @param client object with client data
     * @return the call of the save method of the class ClientRepository if the client id don´t exist or is empty else return to client
     */
    public Client save(Client client){
        if(client.getIdClient()==null){
            return clientRepository.save(client);
        }else{
            Optional<Client> client1= clientRepository.getClient(client.getIdClient());
            if(client1.isEmpty()){
                return clientRepository.save(client);
            }else{
                return client;
            }
        }
    }

    /**
     * UPDATE
     * @param client object with client data
     * @return the call of the update method of the class ClientRepository if the client exist else return to client
     */
    public Client update(Client client){
        if(client.getIdClient()!=null){
            Optional<Client> clientUpdate= clientRepository.getClient(client.getIdClient());
            if(clientUpdate.isPresent()){
                if(client.getName()!=null){
                    clientUpdate.get().setName(client.getName());
                }
                if(client.getEmail()!= null){
                    clientUpdate.get().setEmail(client.getEmail());
                }
                if(client.getAge()!= null){
                    clientUpdate.get().setAge(client.getAge());
                }
                clientRepository.save(clientUpdate.get());
                return clientUpdate.get();
            }else{
                return client;
            }
        }else{
            return client;
        }
    }

    /**
     * DELETE
     * @param clientId client id to delete
     * @return true if the client is deleted else return false
     */
    public boolean deleteClient(Integer clientId) {
        return getClient(clientId).map(client -> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);
    }
}
