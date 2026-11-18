package com.vtuber.wafuseph_backend.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest (
    //parms
    @NotBlank (message="Email is required")
    @Email (message="Email must be valid")
     String email,

    //do not put @Size or any password policy
    @NotBlank (message="Password is required")
    String password
) {}

    

