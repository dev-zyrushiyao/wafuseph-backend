package com.vtuber.wafuseph_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vtuber.wafuseph_backend.model.Contact;

@Repository 
public interface ContactRepo extends JpaRepository<Contact, Long> {
    
}
