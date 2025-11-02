package com.onlinebankingloanmanagement.loanmanagement.controller;

import com.onlinebankingloanmanagement.loanmanagement.dto.JwtAuthResponse;
import com.onlinebankingloanmanagement.loanmanagement.dto.LoginRequest;
import com.onlinebankingloanmanagement.loanmanagement.dto.UserRegisterRequest;
import com.onlinebankingloanmanagement.loanmanagement.dto.UserResponse;
import com.onlinebankingloanmanagement.loanmanagement.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        JwtAuthResponse jwtAuthResponse = authService.login(loginRequest);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRegisterRequest registerRequest) {
        UserResponse savedUser = authService.register(registerRequest);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}