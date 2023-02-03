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
import com.user.Entity.Role;
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
    private final UserRepository userRepository = null;
    private final PasswordEncoder passwordEncoder = null;

    private final JwtService jwtService = new JwtService();
    private final AuthenticationManager authenticationManager = null;

    private static AuthenticationResponse hideUserPassword(User savedUser, String token) {
        return AuthenticationResponse.builder()
                .token(token)
                .id(savedUser.getId())
                .name(savedUser.getName())
                .emailId(savedUser.getEmailId())
                .password("***************")//Hide the password and don't send the real Password
                .adminUser(savedUser.isAdminUser())
                .build();
    }

    public Optional<User> retrieveUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> retrieveUserByEmail(String email) {
        return userRepository.findByEmailId(email);
    }

    public AuthenticationResponse addUser(RegisterRequest registerRequest) {
        userRepository.findByEmailId(registerRequest.getEmailId()).ifPresent(x -> {
            throw new UserServiceException("User Registration Error: Email Already Registered");
        });
        var user = User.builder()
                .name(registerRequest.getName())
                .emailId(registerRequest.getEmailId())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .subscriptions(new ArrayList<>())
                .authorUser(registerRequest.isAuthorUser())
                .role(registerRequest.isAuthorUser() ? Role.ADMIN : Role.USER)
                .build();

        User newUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user.getEmailId());
        return hideUserPassword(newUser, jwtToken);
    }

    public AuthenticationResponse loginUser(LoginRequest registerRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(registerRequest.getEmailId(), registerRequest.getPassword()));
        var user = userRepository.findByEmailId(registerRequest.getEmailId()).orElseThrow(() -> new UserServiceException("Login Error: User or Password Invalid"));
        var jwtToken = jwtService.generateToken(user.getEmailId());
        return hideUserPassword(user, jwtToken);
    }
}