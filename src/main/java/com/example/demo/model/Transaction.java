package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "date")
    private LocalDate transactionDate;

    @Column(name = "comment")
    private String comment;

    @Column(name = "amount")
    private double amount;

    @ManyToOne
    @JoinColumn(name = "accountNumber")
    private Account account;

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionDate=" + transactionDate +
                ", comment='" + comment + '\'' +
                ", amount=" + amount +
                ", account=" + account +
                '}';
    }

    public Transaction() {
    }

    public Transaction(LocalDate transactionDate, String comment, double amount, Account account) {
        this.transactionDate = transactionDate;
        this.comment = comment;
        this.amount = amount;
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
