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
	public String updateUser(User user) throws UserException
	{
		if(userRepo.findById(user.getId()).isPresent())
		{
			userRepo.deleteById(user.getId());
			userRepo.save(user);
		  return "You are sucessfully updated";
	}else throw new UserServiceException("user not found in DB");
	}
	public Optional<User> findUser(String username)
	{
		return userRepo.findByUserName(username);
	}
//	public Optional<User>  findU(String email)
//	{
//		return userRepo.findByGmail(email);
//	}
	
	public User findU(String username)
	{
		return userRepo.findByGmail(username);
	}
}