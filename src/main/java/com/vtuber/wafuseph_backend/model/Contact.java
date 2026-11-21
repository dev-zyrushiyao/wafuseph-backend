package com.vtuber.wafuseph_backend.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter 
@Setter 
@Table (name="contact")
public class Contact {

    @Setter (AccessLevel.NONE)
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column (nullable=false)
    private String email;

    @Column (name="sender_name", nullable=false)
    private String senderName;

    @Column (columnDefinition="Text" , nullable=false)
    private String message;
    
    @Setter (AccessLevel.NONE)
    @Column (name="created_at")
    private Instant createdAt;

    @PrePersist
    protected void onCreate(){
        Instant now = Instant.now();
        this.createdAt = now;
    }

    
}
