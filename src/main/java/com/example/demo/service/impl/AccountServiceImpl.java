package com.example.demo.service.impl;

import com.example.demo.model.Account;
import com.example.demo.model.Transaction;
import com.example.demo.model.User;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.AccountService;
import com.example.demo.service.TransactionService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Random;

public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @Override
    public Account createAccount() {
        Account account = new Account();
        account.setBalance(0.0);
        account.setAccountNumber(new Random().nextInt());
        accountRepository.save(account);

        return accountRepository.findByAccountNumber(account.getAccountNumber());
    }

    @Override
    public void deposit(double amount, User user) {
        Account account = user.getAccount();
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        Transaction depositTransaction = new Transaction(LocalDate.now(), "Deposit to account", amount, account);
        transactionService.saveTransaction(depositTransaction);
    }

    @Override
    public void withdraw(double amount, User user) {
        Account account = user.getAccount();
        account.setBalance(account.getBalance() - (amount));
        accountRepository.save(account);
        Transaction withDrawTransaction = new Transaction(LocalDate.now(), "Withdraw from account", amount, account);
        transactionService.saveTransaction(withDrawTransaction);
    }
}
