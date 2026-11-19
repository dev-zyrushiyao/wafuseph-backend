package com.vtuber.wafuseph_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtuber.wafuseph_backend.DTO.AnnouncementRequest;
import com.vtuber.wafuseph_backend.service.AnnouncementService;

import jakarta.validation.Valid;


@RestController 
@RequestMapping("/api/admin")
public class AdminController {
    
    private final AnnouncementService announcementService;

    public AdminController(AnnouncementService announcementService){
        this.announcementService = announcementService;
    }

    @PostMapping("/announcement/post")
    public ResponseEntity<String> postAnnouncement(@Valid @RequestBody AnnouncementRequest announcementRequest ){

        boolean posted = announcementService.createAnnouncement(announcementRequest);

        if(posted){
            return ResponseEntity.status(HttpStatus.CREATED).body("Announcement posted successfully");
        }
        
          return ResponseEntity.status(HttpStatus.CONFLICT).body("Announcement post failed");
    }
}
