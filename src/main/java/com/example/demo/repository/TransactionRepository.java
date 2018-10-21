package com.example.demo.repository;

import com.example.demo.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findAll();

    List<Transaction> findByAccountAccountNumber(int accountNumber);
}
