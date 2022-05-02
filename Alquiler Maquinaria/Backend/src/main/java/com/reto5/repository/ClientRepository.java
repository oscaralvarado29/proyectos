package com.reto5.repository;

import java.util.List;
import java.util.Optional;
import com.reto5.intrface.ClientInterface;
import com.reto5.model.Client;
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
     * @return
     */
    public List<Client> getAll(){
        return (List<Client>) clientInterface.findAll();
    }

    /**
     * Select by id
     * @param id
     * @return
     */
    public Optional<Client> getClient(int id){
        return clientInterface.findById(id);
    }

    /**
     * Insert
     * @param client
     * @return
     */
    public Client save(Client client){
        return clientInterface.save(client);
    }

    /**
     * Delete
     * @param client
     */
    public void delete(Client client){
        clientInterface.delete(client);
    }
}
