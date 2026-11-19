package com.vtuber.wafuseph_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtuber.wafuseph_backend.DTO.LoginRequest;
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
    public ResponseEntity<String> register(@Valid  @RequestBody RegistrationRequest registrationRequest) {
        boolean created = userAccountService.registerUser(registrationRequest);

        if(!created){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Account registered");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest) {
        String loginSuccess = userAccountService.loginUser(loginRequest);

        if(loginSuccess != null && !loginSuccess.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(loginSuccess);
        }
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
    }

    // @GetMapping ("/me")
    // public ResponseEntity<String> sampleApi(Authentication authentication){
    //     String email = authentication.getName();

    //     return ResponseEntity.ok("Authenticated as " + email);
    // }

    
}
