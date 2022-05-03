package com.reto5.controller;

import java.util.List;
import java.util.Optional;
import com.reto5.model.Client;
import com.reto5.model.ClientDTO;
import com.reto5.service.ClientService;
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
     * @return clientService.getAll()
     */
    @GetMapping("/all")
    public List<Client> getClients(){
        return clientService.getAll();
    }

    /**
     * GET for specific id
     * @param clientId id of client
     * @return clientService.getClient(clientId)
     */
    @GetMapping("/{id}")
    public Optional<Client> getClient(@PathVariable("id") int clientId) {
        return clientService.getClient(clientId);
    }

    /**
     * POST
     * @param clientDTO DTO of client
     * @return clientService.save(client)
     */
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Client save(@RequestBody ClientDTO clientDTO) {
        Client client = new Client(clientDTO);
        return clientService.save(client);
    }

    /**
     * PUT
     * @param clientDTO DTO of client
     * @return clientService.update(client)
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Client update(@RequestBody ClientDTO clientDTO) {
        Client client = new Client(clientDTO);
        return clientService.update(client);
    }

    /**
     * DELETE
     * @param  clientId id of client
     * @return clientService.deleteClient(clientId)
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") Integer clientId) {
        return clientService.deleteClient(clientId);
    }
}
