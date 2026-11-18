package com.vtuber.wafuseph_backend.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vtuber.wafuseph_backend.DTO.LoginRequest;
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


    //service methods
    public Optional<UserAccount> findByEmail(String email){
        return userAccountRepo.findByEmail(email);
    }

    public boolean registerUser(UserAccount userAccount){
        //search if account email exist
        Optional<UserAccount> searchedAccount = findByEmail(EmailNormalizer.normalize(userAccount.getEmail()));

        
        if(searchedAccount.isEmpty()){
            userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
            userAccountRepo.save(userAccount);
            return true;
        }
        //return false if an email is already exist and did not execute the register
        return false;
    }

    public void deleteUserById(UUID id){
        userAccountRepo.deleteById(id);
    }

    public String loginUser(LoginRequest request){
        //search if email exist in the database
       Optional<UserAccount> user = findByEmail(EmailNormalizer.normalize(request.email()));

       if(user.isPresent()){
        UserAccount account = user.get();
  
            if(passwordEncoder.matches(request.password(), account.getPassword())){
            return jwtService.generateToken(account.getEmail());
            } 
       }

       return null;
    }
}
