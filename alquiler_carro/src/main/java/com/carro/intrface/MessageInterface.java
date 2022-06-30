package com.carro.intrface;

import com.carro.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageInterface extends CrudRepository<Message,Integer>  {
}
