package com.alquiler.intrface;

import com.alquiler.model.Machine;
import org.springframework.data.repository.CrudRepository;

public interface MachineInterface extends CrudRepository<Machine,Integer> {
}
