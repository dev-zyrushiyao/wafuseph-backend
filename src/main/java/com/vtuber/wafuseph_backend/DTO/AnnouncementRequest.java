package com.vtuber.wafuseph_backend.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record  AnnouncementRequest (
    //parms - image is not included here
    
    @NotBlank (message="Title should not be blank")
    @Size(min = 10, max = 50, message = "Title needs to be 10~50 characters")
    String title,

    @Size(max = 30, message = "Subtitle only accept max of 30 characters")
    String subTitle,
    
    @NotBlank(message = "Slug is required")
    String slug,

    @NotBlank (message="Content is required")
    String content

) {}
