package com.carro.repository;
import java.util.List;
import java.util.Optional;
import com.carro.intrface.MessageInterface;
import com.carro.model.Message;
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
     * get all
     * @return the call of the findAll method of the interface messageInterface
     */
    public List<Message> getAll(){
        return (List<Message>) messageInterface.findAll();
    }

    /**
     * get by specific id
     * @param messageId car id to get
     * @return the call of the findById method of the interface messageInterface
     */
    public Optional<Message> getMessage(int messageId){
        return messageInterface.findById(messageId);
    }

    /**
     * Insert
     * @param message objet with car data
     * @return the call of the save method of the interface messageInterface
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
