package com.example.controller;


import com.example.entity.Account;
import com.example.service.AccountService;
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
    public Account register(@RequestBody String username, String password) {

        accountService.registerNewAccount(username, password);  // <----  not at all sure about this

//        return Account.status(201)
//                .body("Success");

        return null;
    }

//    @ExceptionHandler(RuntimeException.class)



}


//accountId integer primary key auto_increment,
//username varchar(255) not null unique,
//password varchar(255)



//attempt at postmapping
//    public Account register(@RequestBody Account newAccount) {
//        Account account = accountMapper.toEntity(newAccount);
//        accountRepository.save(account);
