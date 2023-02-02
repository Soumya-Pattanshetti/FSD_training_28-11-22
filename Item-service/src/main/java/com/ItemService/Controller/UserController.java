package com.ItemService.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ItemService.Entity.OrderDemo;
import com.ItemService.Entity.User;
import com.ItemService.Exception.UserServiceException;
import com.ItemService.service.UserServices;

@RestController
//@SecurityRequirement(name = "security_scheme")
//@CrossOrigin(value="http://localhost:4200/")
public class UserController {
	@Autowired
	UserServices userService;
	@Autowired
	private OrderDemo bookClient;

	@GetMapping("/home")
	public String getHomePage() {

		return "Welcome To Digital Book Home Page"; // text
	}

	@PostMapping("/register")
	public String addUser(@RequestBody User user) throws UserServiceException {
		System.out.println( user.getUsername() +user.getPassword()+user.getRoles());
	if (null != user.getUsername() && null != user.getPassword() && null != user.getRoles()
				&& !user.getUsername().isBlank() && user.getPassword().isBlank() && user.getRoles().isBlank()) {
		return userService.addUser(user);

		} else
			throw new UserServiceException("missing User details Retry with correct details");
	}

	

}