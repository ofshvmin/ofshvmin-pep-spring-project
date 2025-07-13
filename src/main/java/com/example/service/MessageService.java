package com.example.service;

import com.example.entity.Message;
import com.example.entity.Account;

import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final AccountRepository accountRepository;
    @Autowired //<----  not sure if this needs to be here but will put it and see later
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) { //< -- need to add account repo?
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message createMessage(Message message) {
        //message text cannot be blank
        //message text.len cannot be > 255 char
        if(message.getMessageText().isBlank()
                || message.getMessageText() == null
                || message.getMessageText().length() > 255
                || message.getPostedBy() == null)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        //postedBy is a real, existing user
        Optional<Account> validAccount = accountRepository.findById(message.getPostedBy());
        if(validAccount.isPresent()) {
            return messageRepository.save(message);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

}
