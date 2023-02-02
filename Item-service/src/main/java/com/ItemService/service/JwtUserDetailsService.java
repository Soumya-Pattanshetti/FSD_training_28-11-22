package com.ItemService.service;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ItemService.Repositories.UserRepository;
import com.ItemService.util.JwtTokenUtil;



@Service
public class JwtUserDetailsService implements UserDetailsService {
	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        // find user from database where user = :username
	        // userRepo.findByUsername(username);// username, password, roles
	        if ("demo".equals(username)) {
	            return new User(
	                    "demo",
	                    // "{noop}demo@123",
	                    // "{bcrypt}$2a$10$5Q/UZBQH.ArDrvXZfIZrveep0Im/E6rdGRj17uEROHDzRqcWYdI1O",
	                    "$2a$10$5Q/UZBQH.ArDrvXZfIZrveep0Im/E6rdGRj17uEROHDzRqcWYdI1O",
	                    new ArrayList<>());
	        } else {
	            throw new UsernameNotFoundException("User not found with username: " + username);
	        }
	 }
}