package com.reto5.service;

import java.util.List;
import java.util.Optional;

import com.reto5.model.Client;
import com.reto5.repository.ClientRepository;
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
     * @return
     */
    public List<Client> getAll(){
        return clientRepository.getAll();
    }

    /**
     * GET select a specific id
     * @param clientId
     * @return
     */
    public Optional<Client> getClient(int clientId) {
        return clientRepository.getClient(clientId);
    }

    /**
     * POST
     * @param client
     * @return
     */
    public Client save(Client client){
        if(client.getIdClient()==null){
            return clientRepository.save(client);
        }else{
            Optional<Client> e= clientRepository.getClient(client.getIdClient());
            if(e.isEmpty()){
                return clientRepository.save(client);
            }else{
                return client;
            }
        }
    }

    /**
     * UPDATE
     * @param client
     * @return
     */
    public Client update(Client client){
        if(client.getIdClient()!=null){
            Optional<Client> e= clientRepository.getClient(client.getIdClient());
            if(!e.isEmpty()){
                if(client.getName()!=null){
                    e.get().setName(client.getName());
                }
                if(client.getAge()!=null){
                    e.get().setAge(client.getAge());
                }
                if(client.getPassword()!=null){
                    e.get().setPassword(client.getPassword());
                }
                clientRepository.save(e.get());
                return e.get();
            }else{
                return client;
            }
        }else{
            return client;
        }
    }

    /**
     * DELETE
     * @param clientId
     * @return
     */
    public boolean deleteClient(Integer clientId) {
        Boolean aBoolean = getClient(clientId).map(client -> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
