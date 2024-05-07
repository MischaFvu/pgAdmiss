package com.webapp.pgadmiss.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.pgadmiss.entity.User;
import com.webapp.pgadmiss.repository.UserRepository;

@Service
public class UserServiceImpl {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    
    public List<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

}
