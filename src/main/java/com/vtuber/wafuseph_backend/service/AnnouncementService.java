package com.vtuber.wafuseph_backend.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vtuber.wafuseph_backend.DTO.AnnouncementRequest;
import com.vtuber.wafuseph_backend.model.Announcement;
import com.vtuber.wafuseph_backend.repository.AnnouncementRepo;

@Service 
public class AnnouncementService {
    
    private final AnnouncementRepo announcementRepo;
    private final CloudinaryService cloudinaryService;

    public AnnouncementService(AnnouncementRepo announcementRepo , CloudinaryService cloudinaryService){
        this.announcementRepo = announcementRepo;
        this.cloudinaryService = cloudinaryService;
    }

    @SuppressWarnings("null")
    public List<Announcement> getAnnouncements(){
        // Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
         Sort sort = Sort.by(Sort.Direction.DESC, Announcement::getCreatedAt);
       List<Announcement> announcemenList = announcementRepo.findAll(sort);

       return announcemenList;
    }

    public Optional<Announcement> getAnnouncementBySlug(String slug){
        Optional<Announcement> announcement = announcementRepo.findBySlug(slug);

        return announcement;
    }

    public boolean createAnnouncement(AnnouncementRequest request , Optional<MultipartFile> file) throws  IOException{ //or @Nullable MultipartFile file
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

            if (file != null && file.isPresent() && !file.get().isEmpty()) {
                String imageUrl = cloudinaryService.uploadImage(file.get());
                announcement.setImageUrl(imageUrl);
            }else{
                announcement.setImageUrl(null);
            }

            announcementRepo.save(announcement);
            return true;
        }
        return false;
    }
}
