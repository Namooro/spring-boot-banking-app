package com.example.demo.service;

import com.example.demo.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> findTransactionList(String username);

    void saveTransaction(Transaction transaction);

}
