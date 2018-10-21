package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AccountService;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public void save(User user) {
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByName(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User createUser(User user) {
        User newUser = userRepository.findByEmail(user.getEmail());

        if (newUser != null) {
            logger.info("User with username {} already exist.", user.getName());
        } else {
            newUser.setAccount(accountService.createAccount());
            userRepository.save(user);
        }
        return user;
    }

    public boolean checkUserExists(String username, String email) {
        return checkNameExists(username) || checkEmailExists(username);
    }

    public boolean checkNameExists(String username) {
        return null != findByUsername(username);
    }

    public boolean checkEmailExists(String email) {
        return null != findByEmail(email);
    }


}
