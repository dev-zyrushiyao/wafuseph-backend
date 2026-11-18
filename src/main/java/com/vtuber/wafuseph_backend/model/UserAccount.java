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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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


    @NotBlank(message = "Email is required")
    @Email(message="Email must be valid")
    @Size(max = 50, message = "Email must be at most 50 characters")
    @Column(name="email", length=50, nullable=false, unique=true)
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100, message = "Password must be 8-100 characters")
    @Column (length=100 , nullable=false)
    private String password;
    
    @NotBlank(message = "Name is required")
    @Size(max = 20, message = "Name must be at most 20 characters")
    @Column (length=20, nullable=false)
    private String name;
    
    @Setter(AccessLevel.NONE)
    @Column (name="created_at", nullable=false)
    private Instant createdAt;
    
    @Setter(AccessLevel.NONE)
    @Column (name="updated_at", nullable=true)
    private Instant updatedAt;

    @NotNull 
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
