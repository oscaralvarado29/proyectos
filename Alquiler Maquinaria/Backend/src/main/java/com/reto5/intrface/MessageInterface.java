package com.reto5.intrface;

import com.reto5.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageInterface extends CrudRepository<Message,Integer>  {
}
