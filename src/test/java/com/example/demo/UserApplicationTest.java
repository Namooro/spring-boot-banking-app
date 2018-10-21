package com.example.demo;

import com.example.demo.model.Account;
import com.example.demo.model.User;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
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
    private double amount = 5.0;

    @Test
    public void isEmailExists() {
        User user = new User();
        String firstEmail = "test@gmail";
        user.setEmail(firstEmail);
        userRepository.save(user);
        assertNotNull(userRepository.findByEmail(firstEmail));
        assertEquals(firstEmail, userRepository.findByEmail(firstEmail).getEmail());
    }

    @Test
    public void isNameExists() {
        User user = new User();
        String firstUsername = "username";
        user.setName(firstUsername);
        assertNotNull(userRepository.findByName(firstUsername));
        assertEquals(firstUsername, userRepository.findByEmail(firstUsername).getName());
    }

    @Test
    public void isAccountExists() {
        Account account = new Account();
        account.setAccountNumber(firstAccountNumber);
        accountRepository.save(account);
        assertNotNull(accountRepository.findByAccountNumber(firstAccountNumber));
        assertEquals(account, accountRepository.findByAccountNumber(firstAccountNumber));
    }

    @Test
    public void isWithdrawPerformed() {
        Account account = new Account();
        account.setAccountNumber(firstAccountNumber);
        account.setBalance(0.0);
        accountRepository.save(account);
        User user = new User();
        user.setAccount(account);
        userRepository.save(user);
        accountService.withdraw(amount, user);
        Account newAccount = accountRepository.findByAccountNumber(firstAccountNumber);
        assertNotNull(newAccount);
        assertEquals(-5.0d, newAccount.getBalance(), 0.1);
    }

    @Test
    public void isDepositPerformed() {
        Account account = new Account();
        account.setAccountNumber(firstAccountNumber);
        account.setBalance(0.0);
        accountRepository.save(account);
        User user = new User();
        user.setAccount(account);
        userRepository.save(user);
        accountService.deposit(amount, user);
        Account newAccount = accountRepository.findByAccountNumber(firstAccountNumber);
        assertNotNull(newAccount);
        assertEquals(5.0d, newAccount.getBalance(), 0.1);
    }

    @Test
    public void getTransactionListFromAccount() {
        Account account = new Account();
        account.setAccountNumber(firstAccountNumber);
        account.setBalance(0.0);
        accountRepository.save(account);
        User user = new User();
        user.setAccount(account);
        userRepository.save(user);
        accountService.withdraw(amount, user);
        accountService.deposit(amount, user);
        assertEquals(2, transactionRepository.findByAccountNumber(firstAccountNumber).size());
    }


}
