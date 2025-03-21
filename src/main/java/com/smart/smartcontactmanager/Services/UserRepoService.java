package com.smart.smartcontactmanager.Services;

import com.smart.smartcontactmanager.Entities.USER;
import com.smart.smartcontactmanager.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRepoService {

    @Autowired
    private UserRepository userRepository;

    public boolean isUserExistsByUserName(USER user){
        return userRepository.existsByUserName(user.getUserName());
    }

    public  boolean isUserExistsByEmail(USER user){
        return userRepository.existsByEmail(user.getEmail());
    }

    public USER registerUser(USER user){
        return userRepository.save(user);
    }

    public USER getUser(String userName){
        return userRepository.findUSERByUserName(userName);
    }

    public USER saveUser(USER user){
        return registerUser(user);
    }

}
