package com.event.eventManagement.service;

import com.event.eventManagement.entity.ContactMessage;
import com.event.eventManagement.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactMessageService {

    @Autowired
    private ContactMessageRepository repository;

    public ContactMessage saveMessage(ContactMessage message) {
        return repository.save(message);
    }

    public List<ContactMessage> getAllMessages() {
        return repository.findAll();
    }

    public String deleteMessage(Long id) {
        repository.deleteById(id);
        return "Message deleted successfully";
    }
}