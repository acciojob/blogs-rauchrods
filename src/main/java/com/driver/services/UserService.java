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
        User user = userRepository3.findById(userId).get();
        userRepository3.delete(user);
    }

    public void updateUser(User user){
       User olduser = userRepository3.findByUsername(user.getUsername());

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
