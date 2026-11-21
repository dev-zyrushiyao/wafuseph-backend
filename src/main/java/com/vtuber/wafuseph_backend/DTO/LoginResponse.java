package com.vtuber.wafuseph_backend.DTO;

public record LoginResponse(
    String token,
    String tokenType,
    String email,
    String name,
    String role
) {}