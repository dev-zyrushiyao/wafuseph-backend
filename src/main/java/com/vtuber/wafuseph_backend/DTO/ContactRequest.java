package com.vtuber.wafuseph_backend.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ContactRequest(
    
    @Email (message="Invalid email format")
    @NotBlank (message="Email is required")
    String email,

    @NotBlank(message="Name is required")
    @Size (max=20 , message="Name should not exceed of 20 characters")
    String senderName,

    @NotBlank (message="Message is required")
    @Size (max=500 , message="Message should not exceed to 500 characters")
    String message

) {}
