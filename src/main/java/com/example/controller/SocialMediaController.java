package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
public class SocialMediaController {

    private final AccountService accountService;
    private final MessageService messageService;

    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> handleAddAccount (@RequestBody Account account) {
        Account createdAccount = accountService.addAccount(account);

        return ResponseEntity.ok(createdAccount);
    }

    @PostMapping("/login")
    public ResponseEntity<Account> handleLogin (@RequestBody Account account) {
        Account loggedInAccount = accountService.login(account);

        return ResponseEntity.ok(loggedInAccount);
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> handleCreateMessage(@RequestBody Message message) {
        Message createdMessage = messageService.createMessage(message);

        return ResponseEntity.ok(createdMessage);
    }

    @GetMapping("/messages")
    public ResponseEntity<List> handleGetAllMessages() {
        List<Message> allMessages = messageService.getAllMessages();
        return ResponseEntity.ok(allMessages);
    }
}