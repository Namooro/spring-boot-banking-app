package com.example.demo.service.impl;

import com.example.demo.model.Transaction;
import com.example.demo.model.User;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.TransactionService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;


    public List<Transaction> findTransactionList(String username) {
        User user = userService.findByUsername(username);
        return user.getAccount().getTransactionList();
    }

    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

}
