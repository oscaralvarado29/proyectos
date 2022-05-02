package com.reto5.repository;

import java.util.List;
import java.util.Optional;
import com.reto5.intrface.MessageInterface;
import com.reto5.model.Message;
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
     * @return
     */
    public List<Message> getAll(){
        return (List<Message>) messageInterface.findAll();
    }

    /**
     * Select by id
     * @param id
     * @return
     */
    public Optional<Message> getMessage(int id){
        return messageInterface.findById(id);
    }

    /**
     * Insert
     * @param message
     * @return
     */
    public Message save(Message message){
        return messageInterface.save(message);
    }

    /**
     * Delete
     * @param message
     */
    public void delete(Message message){
        messageInterface.delete(message);
    }
}
