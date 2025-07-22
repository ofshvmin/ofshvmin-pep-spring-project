package com.example.service;

import com.example.entity.Message;
import com.example.entity.Account;

import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
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

    public Optional<Message> getMessageById(Integer messageId) {
            return messageRepository.findById(messageId);
    }

    public int deleteMessageById(Integer messageId) {
        if(messageRepository.existsById(messageId)) {
            messageRepository.deleteById(messageId);
            return 1;
        } else {
            return 0;
        }
    }

    public int updateMessageText(Integer messageId, String newMessageText) {
        if (newMessageText == null || newMessageText.isBlank() || newMessageText.length() > 255) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid message text");
        }

        Optional<Message> messageOptional = messageRepository.findById(messageId);
        if (messageOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message ID not found");
        }

        Message messageToUpdate = messageOptional.get();
        messageToUpdate.setMessageText(newMessageText);
        messageRepository.save(messageToUpdate);

        return 1;
    }
}
