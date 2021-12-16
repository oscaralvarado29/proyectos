package com.usa.hackaton.service;

import java.util.List;
import java.util.Optional;

import com.usa.hackaton.model.User;
//import com.usa.hackaton.repository.UsernameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oscar Alvarado
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * GET
     * @return
     */
    public List<User> getAll(){
        return userRepository.getAll();
    }

    /**
     * GET select a specific id
     * @param userId
     * @return
     */
    public Optional<User> getUser(int userId) {
        return userRepository.getUser(userId);
    }

    /**
     * POST
     * @param user
     * @return
     */
    public User save(User user){
        if(user.getIdUser() ==null){
            return clientRepository.save(client);
        }else{
            Optional<User> e= clientRepository.getClient(client.getIdUsername());
            if(e.isEmpty()){
                return clientRepository.save(client);
            }else{
                return client;
            }
        }
    }

    /**
     * UPDATE
     * @param client
     * @return
     *
    public User update(User client){
        if(client.getIdUsername()!=null){
            Optional<User> e= clientRepository.getUsername(client.getIdUsername());
            if(!e.isEmpty()){
                if(client.getName()!=null){
                    e.get().setName(client.getName());
                }
                if(client.getAge()!=null){
                    e.get().setAge(client.getAge());
                }
                if(client.getPassword()!=null){
                    e.get().setPassword(client.getPassword());
                }
                clientRepository.save(e.get());
                return e.get();
            }else{
                return client;
            }
        }else{
            return client;
        }
    }

    /**
     * DELETE
     * @param clientId
     * @return
     *
    public boolean deleteClient(Integer clientId) {
        Boolean aBoolean = getClient(clientId).map(client -> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
 */