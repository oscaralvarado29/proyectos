package com.usa.hackaton.intrface;

import com.usa.hackaton.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserInterfaceNew extends CrudRepository<User,Integer> {
}
