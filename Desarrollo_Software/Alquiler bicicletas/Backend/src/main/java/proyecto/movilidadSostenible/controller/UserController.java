package com.usa.hackaton.controller;

import java.util.List;
import java.util.Optional;

import com.usa.hackaton.model.User;
//import com.usa.hackaton.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/**
 *
 * @author Oscar Alvarado
 */
@RestController
@RequestMapping("/Username")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class UserController {
    User user;
    user.get
    @Autowired
    private UserService userService;

    /**
     * GET
     * @return
     *
    @GetMapping("/all")
    public List<User> getUser(){
        return userService.getAll();
    }

    /**
     * GET for specific id
     * @param usernameId
     * @return
     *
    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable("id") int usernameId) {
       // return UserService.getusername(usernameId);
    }

    /**
     * POST
     * @param user
     * @return
     *
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    /**
     * PUT
     * @param user
     * @return
     *
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    /**
     * DELETE
     * @param usernameId
     * @return
     *
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") Integer usernameId) {
        return userService.deleteClient(usernameId);
    }

}
 */