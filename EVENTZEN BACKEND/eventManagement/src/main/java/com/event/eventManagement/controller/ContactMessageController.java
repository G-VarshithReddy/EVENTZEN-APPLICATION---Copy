package com.event.eventManagement.controller;

import com.event.eventManagement.entity.ContactMessage;
import com.event.eventManagement.service.ContactMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactMessageController {

    @Autowired
    private ContactMessageService service;

    // POST request to submit a message
    @PostMapping("/submit")
    public ContactMessage submitMessage(@RequestBody ContactMessage message) {
        return service.saveMessage(message);
    }

    // GET request to retrieve all messages
    @GetMapping("/messages")
    public List<ContactMessage> getAllMessages() {
        return service.getAllMessages();
    }
    // DELETE request to delete a message by ID
    @DeleteMapping("/delete/{id}")
    public String deleteMessage(@PathVariable Long id) {
        return service.deleteMessage(id);
    }
}
