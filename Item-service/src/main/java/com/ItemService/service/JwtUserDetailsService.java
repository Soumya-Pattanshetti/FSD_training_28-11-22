package com.ItemService.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//import com.ItemService.Entity.User;

@Service
public class JwtUserDetailsService implements UserDetailsService {
		@Autowired
	  UserServices userService;
		
	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Optional<com.ItemService.Entity.User> user = userService.findUser(username);
			
			if (null != user) {
				Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
				authorities.add(new SimpleGrantedAuthority("ROLE_" + user.get().getRoles()));
				return new User(user.get().getUsername(), user.get().getPassword(), authorities);
			} else {
				throw new UsernameNotFoundException("User not found with username: " + username);
			}
		} 
		 
		} 
//	        // find user from database where user = :username
//	        // userRepo.findByUsername(username);// username, password, roles
//	        if ("demo".equals(username)) {
//	            return new User(
//	                    "demo",
//	                    // "{noop}demo@123",
//	                    // "{bcrypt}$2a$10$5Q/UZBQH.ArDrvXZfIZrveep0Im/E6rdGRj17uEROHDzRqcWYdI1O",
//	                    "$2a$10$5Q/UZBQH.ArDrvXZfIZrveep0Im/E6rdGRj17uEROHDzRqcWYdI1O",
//	                    new ArrayList<>());
//	        } else {
//	            throw new UsernameNotFoundException("User not found with username: " + username);
//	        }
	 
