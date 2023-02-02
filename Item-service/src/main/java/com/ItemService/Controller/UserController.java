package com.ItemService.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    @GetMapping("/")
    public String getHomePage(){
        return "Home Page"; // text
    }

    @GetMapping("/greet")
    public String greetUser(){
        return "Welcome user";
    }

}