package com.usa.hackaton.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.usa.hackaton.intrface.UserInterfaceNew;

@Repository
public class UserRepository{
    @Autowired
    private UserInterfaceNew userInterface;
}
