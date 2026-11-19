package com.vtuber.wafuseph_backend.DTO;

import com.vtuber.wafuseph_backend.model.constants.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegistrationRequest(

    @NotBlank(message = "Email is required")
    @Email(message="Email must be valid")
    @Size(max = 50, message = "Email must be at most 50 characters")
    String email,

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100, message = "Password must be 8-100 characters")
    String password,
    
    @NotBlank(message = "Name is required")
    @Size(max = 20, message = "Name must be at most 20 characters")
    String name,

    @NotNull(message="Gender is required")
    Gender gender
) {

}
