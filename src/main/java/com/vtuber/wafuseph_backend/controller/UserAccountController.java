package com.vtuber.wafuseph_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtuber.wafuseph_backend.model.UserAccount;
import com.vtuber.wafuseph_backend.service.UserAccountService;




@RestController
@RequestMapping("/api/users")
public class UserAccountController {

    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService){
        this.userAccountService = userAccountService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserAccount userAccount) {
        boolean created = userAccountService.registerUser(userAccount);

        if(!created){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Account registered");
    }
    
}
