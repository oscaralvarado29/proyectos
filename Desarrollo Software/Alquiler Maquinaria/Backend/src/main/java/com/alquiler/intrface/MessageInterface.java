package com.alquiler.intrface;

import com.alquiler.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageInterface extends CrudRepository<Message,Integer>  {
}
