package com.carro.controller;

import java.util.List;
import java.util.Optional;

import com.carro.model.Message;
import com.carro.pojo.MessagePojo;
import com.carro.service.MessageService;
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
@RequestMapping("/Message")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class MessageController {
    @Autowired
    private MessageService messageService;

    /**
     * GET
     * @return the call of the getMessages method of the class messageService
     */
    @GetMapping("/all")
    public List<Message> getMessages(){
        return messageService.getAll();
    }

    /**
     * GET for specif id
     * @param messagePojo message id to get
     * @return the call of the getMessages method of the class messageService
     */
    @GetMapping("/{id}")
    public Optional<Message> getMessage(@PathVariable("id") int messageId) {
        return messageService.getMessage(messageId);
    }

    /**
     * POST
     * @param messagePojo pojo created with message data
     * @return the call of the save method of the class messageService
     */
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Message save(@RequestBody MessagePojo messagePojo) {
        Message message = new Message(messagePojo);
        return messageService.save(message);
    }

    /**
     * PUT
     * @param messagePojo pojo created with message data
     * @return the call of the update method of the class messageService
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Message update(@RequestBody MessagePojo messagePojo) {
        Message message = new Message(messagePojo);
        return messageService.update(message);
    }

    /**
     * DELETE
     * @param messagePojo message id to delete
     * @return the call of the delete method of the class messageService
     */
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@RequestBody MessagePojo messagePojo) {
        Message message = new Message(messagePojo);
        return messageService.deleteMessage(message.getIdMessage());
    }
}
