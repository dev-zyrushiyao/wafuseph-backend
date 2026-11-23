package com.vtuber.wafuseph_backend.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(value="/announcement/post" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> postAnnouncement(@Valid @RequestPart("announcementData") AnnouncementRequest announcementRequest , @RequestPart(value = "file", required = false) Optional<MultipartFile> file ) throws IOException{
        IO.println(announcementRequest);
        IO.println(file);

        boolean posted = announcementService.createAnnouncement(announcementRequest , file);

        if(posted){
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message" , "Announcement posted successfully"));
        }
        
          return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message" , "Announcement post failed" ));
    }
}
