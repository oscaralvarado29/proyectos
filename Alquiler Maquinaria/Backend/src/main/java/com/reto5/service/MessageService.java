package com.reto5.service;

import java.util.List;
import java.util.Optional;

import com.reto5.model.Message;
import com.reto5.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oscar Alvarado
 */
@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    /**
     * GET
     * @return
     */
    public List<Message> getAll(){
        return messageRepository.getAll();
    }

    /**
     * GET select a specific id
     * @param messageId
     * @return
     */
    public Optional<Message> getMessage(int messageId) {
        return messageRepository.getMessage(messageId);
    }

    /**
     * POST
     * @param message
     * @return
     */
    public Message save(Message message){
        if(message.getIdMessage()==null){
            return messageRepository.save(message);
        }else{
            Optional<Message> e= messageRepository.getMessage(message.getIdMessage());
            if(e.isEmpty()){
                return messageRepository.save(message);
            }else{
                return message;
            }
        }
    }

    /**
     * UPDATE
     * @param message
     * @return
     */
    public Message update(Message message){
        if(message.getIdMessage()!=null){
            Optional<Message> e= messageRepository.getMessage(message.getIdMessage());
            if(!e.isEmpty()){
                if(message.getMessageText()!=null){
                    e.get().setMessageText(message.getMessageText());
                }
                messageRepository.save(e.get());
                return e.get();
            }else{
                return message;
            }
        }else{
            return message;
        }
    }

    /**
     * DELETE
     * @param messageId
     * @return
     */
    public boolean deleteMessage(Integer messageId) {
        Boolean aBoolean = getMessage(messageId).map(message -> {
            messageRepository.delete(message);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
