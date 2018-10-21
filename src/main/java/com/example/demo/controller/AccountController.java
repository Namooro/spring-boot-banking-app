package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.model.Transaction;
import com.example.demo.model.User;
import com.example.demo.service.AccountService;
import com.example.demo.service.TransactionService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @RequestMapping("/account")
    public String account(Model model, User user) {
        Account account = user.getAccount();
        model.addAttribute("account", account);
        return "account";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.GET)
    public String deposit(Model model) {
        model.addAttribute("amount", "");
        return "deposit";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public String depositPOST(@ModelAttribute("amount") String amount,
                              User user) {
        accountService.deposit(Double.parseDouble(amount), user);
        return "redirect:/account";
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.GET)
    public String withdraw(Model model) {
        model.addAttribute("amount", "");
        return "withdraw";
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public String withdrawPOST(@ModelAttribute("amount") String amount,
                               User user) {
        accountService.withdraw(Double.parseDouble(amount), user);
        return "redirect:/account";
    }

    @RequestMapping("/account")
    public String primaryAccount(Model model, User user) {
        List<Transaction> transactionList = transactionService
                .findTransactionList(user.getName());
        Account account = user.getAccount();
        model.addAttribute("account", account);
        model.addAttribute("transactionList", transactionList);
        return "account";
    }
}
