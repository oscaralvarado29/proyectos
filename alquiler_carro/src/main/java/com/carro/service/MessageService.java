package com.carro.service;

import java.util.List;
import java.util.Optional;

import com.carro.model.Message;
import com.carro.repository.MessageRepository;
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
     * GET ALL
     * @return  the call of the getAll method of the class messageRepository
     */
    public List<Message> getAll(){
        return messageRepository.getAll();
    }

    /**
     * GET by specific id
     * @param messageId message id to get
     * @return the call of the getMessage method of the class messageRepository
     */
    public Optional<Message> getMessage(int messageId) {
        return messageRepository.getMessage(messageId);
    }

    /**
     * POST
     * @param message object with message data
     * @return the call of the save method of the class messageRepository if the message id don´t exist or is empty else return to message
     */
    public Message save(Message message){
        if(message.getIdMessage()==null){
            return messageRepository.save(message);
        }else{
            Optional<Message> message1= messageRepository.getMessage(message.getIdMessage());
            if(message1.isEmpty()){
                return messageRepository.save(message);
            }else{
                return message;
            }
        }
    }

    /**
     * UPDATE
     * @param message message to update
     * @return messageUpdate.get() if message.getIdMessage() is not null and idMessage is not empty else return message
     */
    public Message update(Message message){
        if(message.getIdMessage() != null){
            Optional<Message> messageUpdate= messageRepository.getMessage(message.getIdMessage());
            if(messageUpdate.isPresent()){
                if(message.getMessageText()!=null){
                    messageUpdate.get().setMessageText(message.getMessageText());
                }
                if(message.getScore() < 0.0){
                    messageUpdate.get().setMessageText(message.getMessageText());
                }
                messageRepository.save(messageUpdate.get());
                return messageUpdate.get();
            }else{
                return message;
            }
        }else{
            return message;
        }
    }

    /**
     * DELETE
     * @param messageId id of the message to delete
     * @return true if message is deleted else return false
     */
    public boolean deleteMessage(Integer messageId) {
        return getMessage(messageId).map(message -> {
            messageRepository.delete(message);
            return true;
        }).orElse(false);
    }
}
