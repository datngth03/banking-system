package com.banking.banking_system.controller;

import com.banking.banking_system.dto.request.OtpRequest;
import com.banking.banking_system.dto.request.RegisterRequest;
import com.banking.banking_system.dto.response.AuthResponse;
import com.banking.banking_system.repository.CustomerRepository;
import com.banking.banking_system.security.JwtTokenProvider;
import com.banking.banking_system.service.impl.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public AuthResponse register(@RequestBody @Valid RegisterRequest request, HttpServletRequest servletRequest) {
        return authService.register(request, servletRequest);
    }

    @PostMapping("/login")
    public String  login(@RequestBody @Valid RegisterRequest request) {
        return authService.login(request);
    }
    @PostMapping("/verify-otp")
    public ResponseEntity<AuthResponse> verifyOtp(@RequestBody OtpRequest request, HttpServletRequest servletRequest) {
        String ip = servletRequest.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty()) {
            ip = servletRequest.getRemoteAddr();
        }
        String device = servletRequest.getHeader("User-Agent");

        AuthResponse response = authService.verifyOtp(request, ip, device);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Swagger!";
    }
}
