package com.vehicle.intrface;

import com.vehicle.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageInterface extends CrudRepository<Message,Integer>  {
}
