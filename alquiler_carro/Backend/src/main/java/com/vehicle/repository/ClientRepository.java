package com.vehicle.repository;

import java.util.List;
import java.util.Optional;
import com.vehicle.intrface.ClientInterface;
import com.vehicle.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Oscar Alvarado
 */
@Repository
public class ClientRepository {
    @Autowired
    private ClientInterface clientInterface;

    /**
     * get all
     * @return the call of the findAll method of the interface ClientInterface
     */
    public List<Client> getAll(){
        return (List<Client>) clientInterface.findAll();
    }

    /**
     * get by specific id
     * @param clientId client id to get
     * @return the call of the findById method of the interface ClientInterface
     */
    public Optional<Client> getClient(int clientId){
        return clientInterface.findById(clientId);
    }

    /**
     * Insert
     * @param client objet with client data
     * @return the call of the save method of the interface ClientInterface
     */
    public Client save(Client client){
        return clientInterface.save(client);
    }

    /**
     * Delete
     * @param client objet with client data to delete
     */
    public void delete(Client client){
        clientInterface.delete(client);
    }
}
