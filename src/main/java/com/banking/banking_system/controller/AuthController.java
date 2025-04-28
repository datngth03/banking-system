package com.banking.banking_system.controller;

import com.banking.banking_system.dto.request.OtpRequest;
import com.banking.banking_system.dto.request.RegisterRequest;
import com.banking.banking_system.dto.response.AuthResponse;
import com.banking.banking_system.repository.CustomerRepository;
import com.banking.banking_system.security.JwtTokenProvider;
import com.banking.banking_system.service.impl.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authManager;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtProvider;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody @Valid RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public String  login(@RequestBody @Valid RegisterRequest request) {
        return authService.login(request);
    }
    @PostMapping("/verify-otp")
    public AuthResponse verifyOtp(@RequestBody OtpRequest request) {
        return authService.verifyOtp(request);
    }
    @GetMapping("/hello")
    public String hello() {
        return "Hello Swagger!";
    }
}
