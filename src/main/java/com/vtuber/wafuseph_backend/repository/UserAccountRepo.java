package com.vtuber.wafuseph_backend.repository;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vtuber.wafuseph_backend.model.UserAccount;

@Repository 
public interface  UserAccountRepo extends JpaRepository<UserAccount, UUID> {
    Optional<UserAccount> findByEmail(String email);
  
}
