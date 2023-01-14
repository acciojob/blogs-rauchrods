package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    @Autowired
    BlogService blogService3;

    public void createUser(User user){
        userRepository3.save(user);
    }

    public void deleteUser(int userId){

        userRepository3.deleteById(userId);
    }

    public void updateUser(User user){
        if(user==null){
            userRepository3.save(user);
            return;
        }

       User olduser = findUserByUsername(user.getUsername());
       olduser.setUsername(user.getUsername());
       olduser.setFirstName(user.getFirstName());
       olduser.setLastName(user.getLastName());
       olduser.setPassword(user.getPassword());
       userRepository3.save(olduser);
    }

    public User findUserByUsername(String username){
        return userRepository3.findByUsername(username);
    }
}
