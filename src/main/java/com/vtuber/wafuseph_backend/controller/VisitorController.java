package com.vtuber.wafuseph_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtuber.wafuseph_backend.model.Announcement;
import com.vtuber.wafuseph_backend.service.AnnouncementService;


//Controller for Wafseph Webpage (visitors)
@RestController 
@RequestMapping ("/api/visitor/view")
public class VisitorController {
    
    private final AnnouncementService announcementService;

    public VisitorController(AnnouncementService announcementService){
        this.announcementService = announcementService;
    }

    @GetMapping("/announcements")
    ResponseEntity<List<Announcement>> displayAnnouncement(){

        //return [] or [...]
        List<Announcement> announcementList = announcementService.getAnnouncements();
        return ResponseEntity.ok().body(announcementList);
        // return ResponseEntity.status(HttpStatus.OK).body(announcementList);
    }

    @GetMapping("/announcements/{slug}")
    public ResponseEntity<Announcement> getMethodName(@PathVariable String slug) {
        // Optional<Announcement> announcement = announcementService.getAnnouncementBySlug(slug);
        return announcementService.getAnnouncementBySlug(slug).map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }
    
}
