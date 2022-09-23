package com.alquiler.intrface;

import com.alquiler.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientInterface extends CrudRepository<Client,Integer> {
}
