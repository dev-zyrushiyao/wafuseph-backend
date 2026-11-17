package com.vtuber.wafuseph_backend.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.vtuber.wafuseph_backend.model.UserAccount;
import com.vtuber.wafuseph_backend.repository.UserAccountRepo;

@Service 
public class UserAccountService {

    private final UserAccountRepo userAccountRepo;
    
    public UserAccountService(UserAccountRepo userAccountRepo){
        this.userAccountRepo = userAccountRepo;
    }


    //service methods
    public Optional<UserAccount> findByEmail(String email){
        return userAccountRepo.findByEmail(email);
    }

    public boolean registerUser(UserAccount userAccount){
        Optional<UserAccount> searchedAccount = findByEmail(userAccount.getEmail());
        
        if(searchedAccount.isEmpty()){
            userAccountRepo.save(userAccount);
            return true;
        }

        //return false if an email is already exist and did not execute the register
        return false;
    }

    public void deleteUserById(UUID id){
        userAccountRepo.deleteById(id);
    }
}
