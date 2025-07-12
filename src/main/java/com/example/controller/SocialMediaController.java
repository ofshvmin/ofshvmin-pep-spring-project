package com.example.controller;


import com.example.entity.Account;
import com.example.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
public class SocialMediaController {

    private final AccountService accountService;

    public SocialMediaController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping("register")
    public ResponseEntity<Account> handleAddAccount (@RequestBody Account account) {
        Account createdAccount = accountService.addAccount(account);

        return ResponseEntity.ok(createdAccount);
    }
//    @ExceptionHandler(ResourceNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public String handleResourceNotFoundException(ResourceNotFoundException ex) {
//        return "Resource not found: " + ex.getMessage();
//    }
}
