package com.alquiler.repository;

import java.util.List;
import java.util.Optional;
import com.alquiler.intrface.MessageInterface;
import com.alquiler.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Oscar Alvarado
 */
@Repository
public class MessageRepository {
    @Autowired
    private MessageInterface messageInterface;

    /**
     * Select
     * @return messageInterface.findAll()
     */
    public List<Message> getAll(){
        return (List<Message>) messageInterface.findAll();
    }

    /**
     * Select by id
     * @param id id of the message
     * @return messageInterface.findById(id)
     */
    public Optional<Message> getMessage(int id){
        return messageInterface.findById(id);
    }

    /**
     * Save
     * @param message  message to save
     * @return messageInterface.save(message)
     */
    public Message save(Message message){
        return messageInterface.save(message);
    }

    /**
     * Delete
     * @param message  message to delete
     */
    public void delete(Message message){
        messageInterface.delete(message);
    }
}
