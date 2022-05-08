package com.reto5.controller;

import java.util.List;
import java.util.Optional;

import com.reto5.model.Message;
import com.reto5.model.MessagePOJO;
import com.reto5.service.MessageService;
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
     * @return messageService.getAll()
     */
    @GetMapping("/all")
    public List<Message> getMessages(){
        return messageService.getAll();
    }

    /**
     * GET for specif id
     * @param messageId id of the message
     * @return messageService.getMessage(messageId)
     */
    @GetMapping("/{id}")
    public Optional<Message> getMessage(@PathVariable("id") int messageId) {
        return messageService.getMessage(messageId);
    }

    /**
     * POST
     * @param messagePOJO POJO of the message
     * @return messageService.save(message)
     */
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Message save(@RequestBody MessagePOJO messagePOJO) {
        Message message = new Message(messagePOJO);
        return messageService.save(message);
    }

    /**
     * PUT
     * @param messagePOJO POJO of the message
     * @return messageService.update(message)
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Message update(@RequestBody MessagePOJO messagePOJO) {
        Message message = new Message(messagePOJO);
        return messageService.update(message);
    }

    /**
     * DELETE
     * @param messageId id of the message
     * @return messageService.deleteMessage(messageId)
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") Integer messageId) {
        return messageService.deleteMessage(messageId);
    }
}
