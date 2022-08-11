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
            return userRepository.save(user);
        }else{
            Optional<User> e= userRepository.getUser(user.getIdUser());
            if(e.isEmpty()){
                return userRepository.save(user);
            }else{
                return user;
            }
        }
    }

    /**
     *
     * @param user
     * @return
     */
    public User update(User user){
        if(user.getIdUser()!=null){
            Optional<User> e= userRepository.getUser(user.getIdUser());
            if(!e.isEmpty()){
                if(user.getName()!=null){
                    e.get().setName(user.getName());
                }
                if(user.getAge()!=null){
                    e.get().setAge(user.getAge());
                }
                if(user.getPhoneNumber()!=null){
                    e.get().setPhoneNumber(user.getPhoneNumber());
                }
                if(user.getAddress()!=null){
                    e.get().setAddress(user.getAddress());
                }
                if(user.getNeighborhood()!=null){
                    e.get().setNeighborhood(user.getNeighborhood());
                }
                if(user.getGender()!=null){
                    e.get().setGender(user.getGender());
                }
                userRepository.save(e.get());
                return e.get();
            }else{
                return user;
            }
        }else{
            return user;
        }
    }

    /**
     * DELETE
     * @param userId
     * @return
     */
    public boolean deleteClient(Integer userId) {
        Boolean aBoolean = getUser(userId).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
