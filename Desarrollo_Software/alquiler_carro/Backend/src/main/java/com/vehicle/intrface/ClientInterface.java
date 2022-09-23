package com.vehicle.intrface;

import com.vehicle.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientInterface extends CrudRepository<Client,Integer> {
}
