package com.smartinventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartinventory.dto.JwtResponse;
import com.smartinventory.dto.LoginRequest;
import com.smartinventory.dto.RegisterRequest;
import com.smartinventory.entity.User;
import com.smartinventory.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {

        return userService.registerUser(request);
    }
    
    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest request) {

        String token = userService.login(request);

        return new JwtResponse(token);
    }
}