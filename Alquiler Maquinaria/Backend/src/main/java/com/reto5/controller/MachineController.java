package com.reto5.controller;

import java.util.List;
import java.util.Optional;

import com.reto5.model.Machine;
import com.reto5.model.MachinePOJO;
import com.reto5.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Oscar Alvarado
 */
@RestController
@RequestMapping("/Machine")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})

public class MachineController {
    @Autowired
    private MachineService machineService;

    /**
     * GET
     * @return machineService.getAll()
     */
    @GetMapping("/all")
    public List<Machine> getMachines(){
        return machineService.getAll();
    }

    /**
     * GET for specific id
     * @param machineId id of the machine
     * @return machineService.getMachine(machineId)
     */
    @GetMapping("/{id}")
    public Optional<Machine> getMachine(@PathVariable("id") int machineId) {
        return machineService.getMachine(machineId);
    }

    /**
     * POST
     * @param machinePOJO POJO of machine
     * @return machineService.save(machine)
     */
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Machine save(@RequestBody MachinePOJO machinePOJO) {
        Machine machine = new Machine(machinePOJO);
        return machineService.save(machine);
    }

    /**
     * PUT
     * @param machinePOJO POJO of machine
     * @return machineService.update(machine)
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Machine update(@RequestBody MachinePOJO machinePOJO) {
        Machine machine = new Machine(machinePOJO);
        return machineService.update(machine);
    }

    /**
     *DELETE
     * @param machineId id of the machine
     * @return machineService.deleteMachine(machineId)
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") Integer machineId) {
        return machineService.deleteMachine(machineId);
    }
}
