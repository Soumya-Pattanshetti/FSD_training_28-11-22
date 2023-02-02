package com.user.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import com.user.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    //Optional<User> findByCompensationPlanId(Long compensationPlanId);
    Optional<User> findByEmailId(String emailId);
    
}
