package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    User findByUsername(String username);

    User findByEmail(String email);

    boolean checkUserExists(String username, String email);

    boolean checkNameExists(String username);

    boolean checkEmailExists(String email);

    void save(User user);

    User createUser(User user);
}
