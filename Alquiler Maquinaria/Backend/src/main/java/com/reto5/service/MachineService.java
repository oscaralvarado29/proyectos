package com.reto5.service;

import java.util.List;
import java.util.Optional;

import com.reto5.model.Machine;
import com.reto5.repository.MachineRepository;
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
     * @return
     */
    public List<Machine> getAll(){
        return machineRepository.getAll();
    }

    /**
     * GET select a specific id
     * @param machineId
     * @return
     */
    public Optional<Machine> getMachine(int machineId) {
        return machineRepository.getMachine(machineId);
    }

    /**
     * POST
     * @param machine
     * @return
     */
    public Machine save(Machine machine){
        if(machine.getId()==null){
            return machineRepository.save(machine);
        }else{
            Optional<Machine> e= machineRepository.getMachine(machine.getId());
            if(e.isEmpty()){
                return machineRepository.save(machine);
            }else{
                return machine;
            }
        }
    }

    /**
     * UPDATE
     * @param machine
     * @return
     */
    public Machine update(Machine machine){
        if(machine.getId()!=null){
            Optional<Machine> e= machineRepository.getMachine(machine.getId());
            if(!e.isEmpty()){
                if(machine.getName()!=null){
                    e.get().setName(machine.getName());
                }
                if(machine.getBrand()!=null){
                    e.get().setBrand(machine.getBrand());
                }
                if(machine.getYear()!=null){
                    e.get().setYear(machine.getYear());
                }
                if(machine.getDescription()!=null){
                    e.get().setDescription(machine.getDescription());
                }
                if(machine.getCategory()!=null){
                    e.get().setCategory(machine.getCategory());
                }
                machineRepository.save(e.get());
                return e.get();
            }else{
                return machine;
            }
        }else{
            return machine;
        }
    }

    /**
     * DELETE
     * @param machineId
     * @return
     */
    public boolean deleteMachine(Integer machineId) {
        Boolean aBoolean = getMachine(machineId).map(machine -> {
            machineRepository.delete(machine);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
