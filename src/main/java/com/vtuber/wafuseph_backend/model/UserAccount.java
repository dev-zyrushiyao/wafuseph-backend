package com.vtuber.wafuseph_backend.model;

import java.time.Instant;
import java.util.UUID;

import com.vtuber.wafuseph_backend.model.constants.Gender;
import com.vtuber.wafuseph_backend.model.constants.Role;

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
import jakarta.validation.constraints.NotNull;
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

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull 
    @Column (nullable=false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
    
    @Setter(AccessLevel.NONE)
    @Column (name="created_at", nullable=false)
    private Instant createdAt;
    
    @Setter(AccessLevel.NONE)
    @Column (name="updated_at", nullable=true)
    private Instant updatedAt;

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
