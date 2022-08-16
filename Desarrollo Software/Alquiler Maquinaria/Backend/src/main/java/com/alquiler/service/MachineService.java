package com.alquiler.service;

import java.util.List;
import java.util.Optional;

import com.alquiler.model.Machine;
import com.alquiler.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oscar Alvarado
 */
@Service
public class MachineService {
    @Autowired
    private MachineRepository machineRepository;

    /**
     * GET
     * @return machineRepository.getAll()
     */
    public List<Machine> getAll(){
        return machineRepository.getAll();
    }

    /**
     * GET select a specific id
     * @param machineId id  of the machine
     * @return machineRepository.getMachine(machineId)
     */
    public Optional<Machine> getMachine(int machineId) {
        return machineRepository.getMachine(machineId);
    }

    /**
     * POST
     * @param machine machine to save
     * @return machineRepository.save(machine) if machine.getId() is null or not exist else return machine
     */
    public Machine save(Machine machine){
        if(machine.getId()==null){
            return machineRepository.save(machine);
        }else{
            Optional<Machine> machine1= machineRepository.getMachine(machine.getId());
            if(machine1.isEmpty()){
                return machineRepository.save(machine);
            }else{
                return machine;
            }
        }
    }

    /**
     * UPDATE
     * @param machine machine to update
     * @return machineUpdate.get() if machine.getId() is not null or exist else return machine
     */
    public Machine update( Machine machine){
        if(machine.getId()!=null){
            Optional<Machine> machineUpdate= machineRepository.getMachine(machine.getId());
            if(machineUpdate.isPresent()){
                if(machine.getName()!=null){
                    machineUpdate.get().setName(machine.getName());
                }
                if(machine.getBrand()!=null){
                    machineUpdate.get().setBrand(machine.getBrand());
                }
                if(machine.getYear()!=null){
                    machineUpdate.get().setYear(machine.getYear());
                }
                if(machine.getDescription()!=null){
                    machineUpdate.get().setDescription(machine.getDescription());
                }
                if(machine.getCategory()!=null){
                    machineUpdate.get().setCategory(machine.getCategory());
                }
                machineRepository.save(machineUpdate.get());
                return machineUpdate.get();
            }else{
                return machine;
            }
        }else{
            return machine;
        }
    }

    /**
     * DELETE
     * @param machineId id of the machine to delete
     * @return true if machine is deleted else return false
     */
    public boolean deleteMachine(Integer machineId) {
        return getMachine(machineId).map(machine -> {
            machineRepository.delete(machine);
            return true;
        }).orElse(false);
    }
}
