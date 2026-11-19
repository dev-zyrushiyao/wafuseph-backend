package com.vtuber.wafuseph_backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vtuber.wafuseph_backend.DTO.AnnouncementRequest;
import com.vtuber.wafuseph_backend.model.Announcement;
import com.vtuber.wafuseph_backend.repository.AnnouncementRepo;

@Service 
public class AnnouncementService {
    
    private final AnnouncementRepo announcementRepo;

    public AnnouncementService(AnnouncementRepo announcementRepo){
        this.announcementRepo = announcementRepo;
    }

    public boolean createAnnouncement(AnnouncementRequest request){

        Optional<Announcement> existing = announcementRepo.findBySlug(request.slug());

        if(existing.isEmpty()){
            //Annoucement request -> React Form
            //SpringBoot upload on Cloudinary
            //setImageURL with Image URL from Cloudinary then save Records
            Announcement announcement = new Announcement();
            announcement.setTitle(request.title());
            announcement.setSubTitle(request.subTitle());
            announcement.setSlug(request.slug());
            announcement.setContent(request.content());
            // announcement.setImageUrl(null);

            announcementRepo.save(announcement);
            return true;
        }

        return false;
    }
}
