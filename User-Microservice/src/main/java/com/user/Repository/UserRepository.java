package com.user.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.user.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    
    List<User> findByNameContainsIgnoreCaseAllIgnoreCase(String name);
    Optional<User> findByEmailIdAndPassword(String emailId, String password);
    Optional<User> findByEmailId(String emailId);
}
