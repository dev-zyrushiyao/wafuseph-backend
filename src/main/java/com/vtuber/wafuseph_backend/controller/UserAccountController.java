package com.vtuber.wafuseph_backend.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtuber.wafuseph_backend.DTO.LoginRequest;
import com.vtuber.wafuseph_backend.DTO.LoginResponse;
import com.vtuber.wafuseph_backend.DTO.RegistrationRequest;
import com.vtuber.wafuseph_backend.service.UserAccountService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class UserAccountController {

    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService){
        this.userAccountService = userAccountService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid  @RequestBody RegistrationRequest registrationRequest) {
             boolean created = userAccountService.registerUser(registrationRequest);

    if (!created) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("registrationStatus", "Account already exist"));
    }

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(Map.of("registrationStatus", "Account successfully registered"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = userAccountService.loginUser(loginRequest);

        if(loginResponse != null){
   
            return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
        }
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message" , "Invalid login credentials"));
    }

    
}
