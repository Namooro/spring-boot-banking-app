package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.model.User;

public interface AccountService {

    Account createAccount();

    void deposit(double amount, User user);

    void withdraw(double amount, User user);
}
