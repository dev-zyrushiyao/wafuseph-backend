package com.vtuber.wafuseph_backend.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vtuber.wafuseph_backend.DTO.LoginRequest;
import com.vtuber.wafuseph_backend.model.UserAccount;
import com.vtuber.wafuseph_backend.repository.UserAccountRepo;
import com.vtuber.wafuseph_backend.util.EmailNormalizer;

@Service 
public class UserAccountService {

    private final UserAccountRepo userAccountRepo;
    private final PasswordEncoder passwordEncoder;
    
    public UserAccountService(UserAccountRepo userAccountRepo , PasswordEncoder passwordEncoder){
        this.userAccountRepo = userAccountRepo;
        this.passwordEncoder = passwordEncoder;
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

    public boolean loginUser(LoginRequest request){
        //search if email exist in the database
       Optional<UserAccount> user = findByEmail(EmailNormalizer.normalize(request.email()));

       if(user.isPresent()){
       UserAccount account = user.get();
       //returns true
      return passwordEncoder.matches(request.password(), account.getPassword());
       }

       return false;
    }
}
