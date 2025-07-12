package com.example.service;

import com.example.repository.AccountRepository;
import org.springframework.stereotype.Component;

public class AccountService {

    private final AccountRepository accountRepository;

    // setter injection
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void registerNewAccount(String user, String pw) {
        System.out.println("The transaction worked!" + user + " " + pw);
    }

}