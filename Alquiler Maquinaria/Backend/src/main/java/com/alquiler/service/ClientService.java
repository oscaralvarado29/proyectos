package com.alquiler.service;

import java.util.List;
import java.util.Optional;

import com.alquiler.model.Client;
import com.alquiler.repository.ClientRepository;
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
     * @return clientRepository.getAll()
     */
    public List<Client> getAll(){
        return clientRepository.getAll();
    }

    /**
     * GET select a specific id
     * @param clientId id of the client
     * @return clientRepository.getClient(clientId)
     */
    public Optional<Client> getClient(int clientId) {
        return clientRepository.getClient(clientId);
    }

    /**
     * POST
     * @param client client to save
     * @return clientRepository.save(client) if client.getIdClient() is null or or clientRepository.getClient(client.getId()).isEmpty()
     * else client
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
     * @param client client to update
     * @return clientUpdate.get() if client.getIdClient() is not null and clientUpdate.isPresent() else return client
     */
    public Client update(Client client){
        if(client.getIdClient()!=null){
            Optional<Client> clientUpdate= clientRepository.getClient(client.getIdClient());
            if(clientUpdate.isPresent()){
                if(client.getName()!=null){
                    clientUpdate.get().setName(client.getName());
                }
                if(client.getAge()!=null){
                    clientUpdate.get().setAge(client.getAge());
                }
                if(client.getPassword()!=null){
                    clientUpdate.get().setPassword(client.getPassword());
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
     * @param clientId id of the client to delete
     * @return true if the client is deleted else return false
     */
    public boolean deleteClient(Integer clientId) {
        return getClient(clientId).map(client -> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);
    }
}
