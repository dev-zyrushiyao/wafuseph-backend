package com.vtuber.wafuseph_backend.service;

import org.springframework.stereotype.Service;
import static org.springframework.util.StringUtils.hasText;

import com.vtuber.wafuseph_backend.DTO.ContactRequest;
import com.vtuber.wafuseph_backend.model.Contact;
import com.vtuber.wafuseph_backend.repository.ContactRepo;

@Service 
public class ContactService {
    
    private final ContactRepo contactRepo;

    public ContactService(ContactRepo contactRepo){
        this.contactRepo = contactRepo;
    }

    public boolean saveContact(ContactRequest contactRequest){
        if(contactRequest == null || !hasText(contactRequest.email()) || !hasText(contactRequest.senderName()) || !hasText(contactRequest.message())) {
            return false;
        }

        Contact contact = new Contact();
        contact.setEmail(contactRequest.email().trim());
        contact.setMessage(contactRequest.message().trim());
        contact.setSenderName(contactRequest.senderName().trim());
        
        contactRepo.save(contact);

        return true;
    }
}
