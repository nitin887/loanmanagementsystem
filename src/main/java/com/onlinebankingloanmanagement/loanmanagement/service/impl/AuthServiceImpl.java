package com.onlinebankingloanmanagement.loanmanagement.service.impl;

import com.onlinebankingloanmanagement.loanmanagement.dto.JwtAuthResponse;
import com.onlinebankingloanmanagement.loanmanagement.dto.LoginRequest;
import com.onlinebankingloanmanagement.loanmanagement.dto.UserRegisterRequest;
import com.onlinebankingloanmanagement.loanmanagement.dto.UserResponse;
import com.onlinebankingloanmanagement.loanmanagement.exception.ResourceNotFoundException; // Assuming you have a custom exception
import com.onlinebankingloanmanagement.loanmanagement.mapper.UserMapper;
import com.onlinebankingloanmanagement.loanmanagement.model.Role;
import com.onlinebankingloanmanagement.loanmanagement.model.User;
import com.onlinebankingloanmanagement.loanmanagement.repository.UserRepository;
import com.onlinebankingloanmanagement.loanmanagement.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    // private final JwtTokenProvider jwtTokenProvider; // You will need to implement this

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse register(UserRegisterRequest registerRequest) {
        // Check if username or email already exists
        if (userRepository.existsByUsername(registerRequest.getUsername()) || userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new IllegalArgumentException("Username or Email is already taken!");
        }

        User user = userMapper.toEntity(registerRequest);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(Role.CUSTOMER); // Default role

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public JwtAuthResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // String token = jwtTokenProvider.generateToken(authentication);
        String token = "dummy-jwt-token-for-now"; // Replace with actual token generation
        return new JwtAuthResponse(token, "Bearer");
    }
}