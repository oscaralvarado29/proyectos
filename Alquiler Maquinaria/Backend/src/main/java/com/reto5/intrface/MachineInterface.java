package com.reto5.intrface;

import com.reto5.model.Machine;
import org.springframework.data.repository.CrudRepository;

public interface MachineInterface extends CrudRepository<Machine,Integer> {
}
