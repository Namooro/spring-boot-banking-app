package com.example.demo;

import com.example.demo.model.Account;
import com.example.demo.model.User;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AccountService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserApplicationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    private int firstAccountNumber = 123456789;
    private String firstUsername = "username";
    private String firstEmail = "test@gmail";
    private String firstPassword = "1";
    private double emptyAmount = 0.0;
    private double amount = 5.0;

    @After
    public void after() {
        userRepository.deleteAll();
        accountRepository.deleteAll();
        transactionRepository.deleteAll();
    }

    @Test
    public void isEmailExists() {
        User user = new User(firstUsername, firstEmail, firstPassword);
        user.setEmail(firstEmail);
        userRepository.save(user);
        assertNotNull(userRepository.findByEmail(firstEmail));
        assertEquals(firstEmail, userRepository.findByEmail(firstEmail).getEmail());
    }

    @Test
    public void isNameExists() {
        User user = new User(firstUsername, firstEmail, firstPassword);
        userRepository.save(user);
        assertNotNull(userRepository.findByName(firstUsername));
        assertEquals(firstUsername, userRepository.findByName(firstUsername).getName());
    }

    @Test
    public void isAccountExists() {
        Account account = new Account(firstAccountNumber, emptyAmount);
        accountRepository.save(account);
        assertNotNull(accountRepository.findByAccountNumber(firstAccountNumber));
        assertEquals(account.getAccountNumber(), accountRepository.findByAccountNumber(firstAccountNumber).getAccountNumber());
    }

    @Test
    public void isWithdrawPerformed() {
        Account account = new Account(firstAccountNumber, emptyAmount);
        accountRepository.save(account);
        User user = new User(firstUsername, firstEmail, firstPassword);
        user.setAccount(account);
        userRepository.save(user);
        accountService.withdraw(amount, user);
        Account newAccount = accountRepository.findByAccountNumber(firstAccountNumber);
        assertNotNull(newAccount);
        assertEquals(-5.0d, newAccount.getBalance(), 0.1);
    }

    @Test
    public void isDepositPerformed() {
        Account account = new Account(firstAccountNumber, emptyAmount);
        accountRepository.save(account);
        User user = new User(firstUsername, firstEmail, firstPassword);
        user.setAccount(account);
        userRepository.save(user);
        accountService.deposit(amount, user);
        Account newAccount = accountRepository.findByAccountNumber(firstAccountNumber);
        assertNotNull(newAccount);
        assertEquals(5.0d, newAccount.getBalance(), 0.1);
    }

    @Test
    public void getTransactionListFromAccount() {
        Account account = new Account(firstAccountNumber, emptyAmount);
        accountRepository.save(account);
        User user = new User(firstUsername, firstEmail, firstPassword);
        user.setAccount(account);
        userRepository.save(user);
        accountService.withdraw(amount, user);
        accountService.deposit(amount, user);
        assertEquals(2, transactionRepository.findByAccountAccountNumber(firstAccountNumber).size());
    }
}
