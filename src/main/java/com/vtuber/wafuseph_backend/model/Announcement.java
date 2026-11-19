package com.vtuber.wafuseph_backend.model;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table (name="announcement")
public class Announcement {
    
    @Setter (AccessLevel.NONE)
    @Id 
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;


    @Column(name="image_url")
    private String imageUrl;
    
    
    @Column(nullable=false)
    private String title;

    @Column(name="sub_title" , nullable=true)
    private String subTitle;
    
    @Column(nullable=false, unique=true)
    private String slug;

    @Column (nullable=false , columnDefinition="TEXT")
    private String content; 

    @Setter(AccessLevel.NONE)
    private Instant createdAt;

    @Setter(AccessLevel.NONE)
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
