package com.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.user.Entity.OrderDemo;
import com.user.Entity.User;
import com.user.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*",allowedHeaders="*")
@RequestMapping("/api/test")
@SecurityRequirement(name = "demo_security_scheme")

public class UserController {
	@Autowired
	private RestTemplate restTemplate;
	
    private final UserService userService = new UserService();
    
    @GetMapping("/all")
    public List<OrderDemo> allAccess() {
      List order = this.restTemplate.getForObject("http://localhost:8085/order/allAccess", List.class);
      return order;
    }
    
    
   
   
}
   
