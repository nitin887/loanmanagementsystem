package com.onlinebankingloanmanagement.loanmanagement.service;

import com.onlinebankingloanmanagement.loanmanagement.dto.JwtAuthResponse;
import com.onlinebankingloanmanagement.loanmanagement.dto.LoginRequest;
import com.onlinebankingloanmanagement.loanmanagement.dto.UserRegisterRequest;
import com.onlinebankingloanmanagement.loanmanagement.dto.UserResponse;

/**
 * Service for handling user authentication and registration.
 */
public interface AuthService {
    UserResponse register(UserRegisterRequest registerRequest);

    JwtAuthResponse login(LoginRequest loginRequest);
}