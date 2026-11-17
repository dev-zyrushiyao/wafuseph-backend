package com.vtuber.wafuseph_backend.model;

import java.time.Instant;
import java.util.UUID;

import com.vtuber.wafuseph_backend.model.constants.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter 
@Table(name = "users")
public class UserAccount {
    
    @Setter(AccessLevel.NONE)
    @Id 
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @Column(name="email", length=50, nullable=false, unique=true)
    private String email;

    @Column (length=100 , nullable=false)
    private String password;
    
    @Column (length=20, nullable=false)
    private String name;
    
    @Setter(AccessLevel.NONE)
    @Column (name="created_at", nullable=false)
    private Instant createdAt;
    
    @Setter(AccessLevel.NONE)
    @Column (name="updated_at", nullable=true)
    private Instant updatedAt;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        this.createdAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }

    
}
