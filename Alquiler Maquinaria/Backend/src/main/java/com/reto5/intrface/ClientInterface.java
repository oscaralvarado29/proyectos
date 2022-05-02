package com.reto5.intrface;

import com.reto5.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientInterface extends CrudRepository<Client,Integer> {
}
