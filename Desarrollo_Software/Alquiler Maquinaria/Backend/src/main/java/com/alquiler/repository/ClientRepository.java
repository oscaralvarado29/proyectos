package com.alquiler.repository;

import java.util.List;
import java.util.Optional;
import com.alquiler.intrface.ClientInterface;
import com.alquiler.model.Client;
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
     * Select
     * @return clientInterface.findAll()
     */
    public List<Client> getAll(){
        return (List<Client>) clientInterface.findAll();
    }

    /**
     * Select by id
     * @param id id of client
     * @return clientInterface.findById(id)
     */
    public Optional<Client> getClient(int id){
        return clientInterface.findById(id);
    }

    /**
     * Save
     * @param client client to save
     * @return clientInterface.save(client)
     */
    public Client save(Client client){
        return clientInterface.save(client);
    }

    /**
     * Delete
     * @param client client to delete
     */
    public void delete(Client client){
        clientInterface.delete(client);
    }
}
