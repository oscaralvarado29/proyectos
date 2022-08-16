package com.alquiler.repository;

import java.util.List;
import java.util.Optional;

import com.alquiler.intrface.MachineInterface;
import com.alquiler.model.Machine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Oscar Alvarado
 */
@Repository
public class MachineRepository {
    @Autowired
    private MachineInterface machineInterface;

    /**
     * Select
     * @return machineInterface.findAll()
     */
    public List<Machine> getAll(){
        return (List<Machine>) machineInterface.findAll();
    }

    /**
     * select by id
     * @param id id of machine
     * @return machineInterface.findById(id)
     */
    public Optional<Machine> getMachine(int id){
        return machineInterface.findById(id);
    }

    /**
     * Save
     * @param machine machine to save
     * @return machineInterface.save(machine)
     */
    public Machine save(Machine machine){
        return machineInterface.save(machine);
    }

    /**
     * Delete
     * @param machine machine to delete
     */
    public void delete(Machine machine){
        machineInterface.delete(machine);
    }
}
