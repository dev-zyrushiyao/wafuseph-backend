package com.vtuber.wafuseph_backend.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtuber.wafuseph_backend.DTO.ContactRequest;
import com.vtuber.wafuseph_backend.service.ContactService;

import jakarta.validation.Valid;



@RestController 
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService){
        this.contactService = contactService;
    }

    @PostMapping("/inquiry")
    public ResponseEntity<Map<String,String>> inquiry(@Valid @RequestBody ContactRequest contactRequest) {
        
        boolean posted = contactService.saveContact(contactRequest);

        if(posted) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "message send successfully"));
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message" , "message failed to send"));
    }
    
    
}
