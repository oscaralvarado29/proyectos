package com.vehicle.controller;

import java.util.List;
import java.util.Optional;
import com.vehicle.model.Client;
import com.vehicle.pojo.ClientPojo;
import com.vehicle.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Oscar Alvarado
 */
@RestController
@RequestMapping("/Client")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class ClientController {
    @Autowired
    private ClientService clientService;

    /**
     * GET
     * @return the call of the getAll method of the class clientService
     */
    @GetMapping("/all")
    public List<Client> getClients(){
        return clientService.getAll();
    }

    /**
     * GET for specific id
     * @param clientId client id to get
     * @return the call of the getClient method of the class clientService
     */
    @GetMapping("/{id}")
    public Optional<Client> getClient(@PathVariable("id") int clientId) {
        return clientService.getClient(clientId);
    }

    /**
     * POST
     * @param clientPojo pojo created with client data
     * @return the call of the save method of the class clientService
     */
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Client save(@RequestBody ClientPojo clientPojo) {
        Client client = new Client(clientPojo);
        return clientService.save(client);
    }

    /**
     * PUT
     * @param clientPojo POJO of client
     * @return clientService.update(client)
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Client update(@RequestBody ClientPojo clientPojo) {
        Client client = new Client(clientPojo);
        return clientService.update(client);
    }

    /**
     * DELETE
     * @param  clientPojo client id to delete
     * @return clientService.deleteClient(clientId)
     */
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@RequestBody ClientPojo clientPojo) {
        Client client = new Client(clientPojo);
        return clientService.deleteClient(client.getIdClient());
    }
}
