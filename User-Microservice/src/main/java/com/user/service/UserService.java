package com.user.service;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.Entity.AuthenticationResponse;
import com.user.Entity.LoginRequest;
import com.user.Entity.RegisterRequest;
import com.user.Entity.User;
import com.user.Exception.UserServiceException;
import com.user.Repository.UserRepository;
import com.user.security.JwtAuthenticationFilter;
import com.user.security.JwtService;


import lombok.RequiredArgsConstructor;

    
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private static AuthenticationResponse generateAuthenticationResponse(User savedUser, String token) {
        return AuthenticationResponse.builder()
                .token(token)
                .Id(savedUser.getDealerId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .emailId(savedUser.getEmailId())
                .role(savedUser.getRole())
                .build();
    }

    public Optional<User> retrieveAllUsers(String emailId ) {
        return userRepository.findByEmailId(emailId);
    }

    public Optional<User> retrieveUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> retrieveUserByEmail(String email) {
        return userRepository.findByEmailId(email);
    }

    public AuthenticationResponse registerUser(RegisterRequest registerRequest) {
        userRepository.findByEmailId(registerRequest.getEmailId()).ifPresent(x -> {
            throw new UserServiceException("User Registration Error: Email Already Registered");
        });
        var user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .emailId(registerRequest.getEmailId())
                .password(passwordEncoder.encode(registerRequest.getPassword())).role(registerRequest.getRole())
                .build();
        User newUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user.getEmailId());
        return generateAuthenticationResponse(newUser, jwtToken);
    }

    public AuthenticationResponse loginUser(LoginRequest registerRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(registerRequest.getEmailId(), registerRequest.getPassword()));
        var user = userRepository.findByEmailId(registerRequest.getEmailId()).orElseThrow(() -> new PartnerCompServiceException("Login Error: User or Password Invalid"));
        var jwtToken = jwtService.generateToken(user.getEmailId());
        return generateAuthenticationResponse(user, jwtToken);
    }
}