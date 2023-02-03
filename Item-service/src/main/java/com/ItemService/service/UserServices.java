package com.ItemService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ItemService.Entity.User;
import com.ItemService.Exception.UserServiceException;
import com.ItemService.Repositories.UserRepository;

import jdk.jshell.spi.ExecutionControl.UserException;

@Service
public class UserServices {

		@Autowired
		UserRepository userRepo;

		public String addUser(User user) 
		 {
			 userRepo.save(user);
			 return "You are sucessfully Registered Please login ";
			 
		 }
		
		public Optional<User> findUser(String username)
		{
			return userRepo.findByUsername(username);
		}
		public User findU(String username)
		{
			return userRepo.findByemail(username);
		}

}