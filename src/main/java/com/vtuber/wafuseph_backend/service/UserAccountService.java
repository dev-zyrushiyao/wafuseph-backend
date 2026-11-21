package com.vtuber.wafuseph_backend.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vtuber.wafuseph_backend.DTO.LoginRequest;
import com.vtuber.wafuseph_backend.DTO.LoginResponse;
import com.vtuber.wafuseph_backend.DTO.RegistrationRequest;
import com.vtuber.wafuseph_backend.model.UserAccount;
import com.vtuber.wafuseph_backend.repository.UserAccountRepo;
import com.vtuber.wafuseph_backend.security.EmailNormalizer;
import com.vtuber.wafuseph_backend.security.JwtService;

@Service 
public class UserAccountService {

    private final UserAccountRepo userAccountRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    
    public UserAccountService(UserAccountRepo userAccountRepo , PasswordEncoder passwordEncoder , JwtService jwtService){
        this.userAccountRepo = userAccountRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }



    public boolean registerUser(RegistrationRequest registrationRequest){
        //search if account email exist
        Optional<UserAccount> account = userAccountRepo.findByEmail(EmailNormalizer.normalize(registrationRequest.email()));

        
        if(account.isEmpty()){
            UserAccount newUser = new UserAccount();
            newUser.setEmail(registrationRequest.email());
            newUser.setPassword(passwordEncoder.encode(registrationRequest.password()));
            newUser.setName(registrationRequest.name());
            newUser.setGender(registrationRequest.gender());
            userAccountRepo.save(newUser);
            return true;
        }
        //return false if an email is already exist and did not execute the register
        return false;
    }

    public void deleteUserById(UUID id){
        userAccountRepo.deleteById(id);
    }

    public LoginResponse loginUser(LoginRequest request){
        //search if email exist in the database
       Optional<UserAccount> user = userAccountRepo.findByEmail(EmailNormalizer.normalize(request.email()));

       if(user.isPresent()){
        UserAccount account = user.get();
        
            if(passwordEncoder.matches(request.password(), account.getPassword())){
                String token = jwtService.generateToken(account.getEmail() , account.getRole().name());
                LoginResponse loginResponse = new LoginResponse(token, "Bearer" , account.getEmail() , account.getName(), account.getRole().name());
                return loginResponse;
            } 
       }

       return null;
    }
}
