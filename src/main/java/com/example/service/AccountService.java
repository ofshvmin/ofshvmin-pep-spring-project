package com.example.service;

import com.example.entity.Account;

import java.util.List;

import com.example.exception.DuplicateUsernameException;
import com.example.exception.InvalidAccountException;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;



@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account addAccount(Account account) {
        System.out.println("The transaction worked!");
        //unique account validation
        List<Account> existingAccounts = getAllAccounts();
        for(Account existingAccount : existingAccounts) {
            if(existingAccount.getUsername().equalsIgnoreCase(account.getUsername())) {
                throw new DuplicateUsernameException("Username already exists.");
            }
        }
        //blank username validation or password too short
        if(account.getUsername().isEmpty() || account.getPassword().length() < 4) {
            throw new InvalidAccountException("Username cannot be blank.");
        }
        //If the registration is not successful for some other reason, the response status should be 400. (Client error)
            //exception thrown from the controller

        //The response status should be 200 OK, which is the default. The new account should be persisted to the database.
        return accountRepository.save(account);
    }

    public Account login(Account account) {
        System.out.println("we made it to the services class");

        String username = account.getUsername();
        String password = account.getPassword();

        if(username == null || username.isBlank() || password == null || password.isBlank()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        } else {
        List<Account> existingAccounts = accountRepository.findAll();
        for(Account existingAccount : existingAccounts) {
            if(existingAccount.getUsername().equals(username) && existingAccount.getPassword().equals(password)){
                return existingAccount;
            }
        }
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");

        }

    }

}