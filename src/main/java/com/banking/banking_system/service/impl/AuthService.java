package com.banking.banking_system.service.impl;

import com.banking.banking_system.dto.request.LoginRequest;
import com.banking.banking_system.dto.request.OtpRequest;
import com.banking.banking_system.dto.request.RegisterRequest;
import com.banking.banking_system.dto.response.AuthResponse;
import com.banking.banking_system.entity.Customer;
import com.banking.banking_system.entity.LoginSession;
import com.banking.banking_system.exception.CustomException;
import com.banking.banking_system.repository.CustomerRepository;
import com.banking.banking_system.repository.LoginSessionRepository;
import com.banking.banking_system.security.JwtTokenProvider;
import com.banking.banking_system.service.inter.AuditLogService;
import com.banking.banking_system.service.impl.LoginAttemptService;
import com.banking.banking_system.service.impl.OtpService;

import com.banking.banking_system.service.impl.LoginSessionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerRepository customerRepository;
    private final LoginSessionRepository loginSessionRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmailService emailService;
    private final AuditLogService auditLogService;
    private final LoginSessionService loginSessionService;
    private final LoginAttemptService loginAttemptService;
    private final OtpService otpService;

    public AuthResponse register(RegisterRequest request, HttpServletRequest servletRequest) {
        if (customerRepository.existsByIdentityNumber(request.getIdentityNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "IdentityNumber already exists");
        }
        if (customerRepository.existsByPhone(request.getPhone())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already exists");
        }

        Customer customer = Customer.builder()
                .username(request.getUsername())
                .identityNumber(request.getIdentityNumber())
                .email(request.getEmail())
                .fullName(request.getFullName())
                .phone(request.getPhone())
                .address(request.getAddress())
                .dateOfBirth(request.getDateOfBirth())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .status("ACTIVE")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        customerRepository.save(customer);

        String ipAddress = servletRequest.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty()) {
            ipAddress = servletRequest.getRemoteAddr();
        }
        auditLogService.log(customer.getId(), "REGISTER", "User registered", ipAddress);

        String token = jwtTokenProvider.generateToken(customer.getUsername());
        return new AuthResponse(token);
    }

    public String login(LoginRequest request) {
        String phone = request.getPhone();

        if (loginAttemptService.isBlocked(phone)) {
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS,
                    "Too many failed login attempts. Try again in a few minutes.");
        }
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(phone, request.getPassword())
            );
            loginAttemptService.loginSucceeded(phone);
        } catch (BadCredentialsException ex) {
            loginAttemptService.loginFailed(phone);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        String otp = generateOtp();
        otpService.saveOtp(request.getPhone(), otp);

        Customer customer = customerRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("User not found"));
        emailService.sendOtpEmail(customer.getEmail(), otp);
        return "OTP has been sent!";
    }


    public AuthResponse verifyOtp(OtpRequest request, String ipAddress, String deviceInfo) {

        if (otpService.isBlocked(request.getPhone())) {
            throw new CustomException("Too many failed attempts. Try again later.");
        }

        if (!otpService.verifyOtp(request.getPhone(), request.getOtp())) {
            throw new CustomException("Invalid or expired OTP");
        }

        Customer customer = customerRepository.findByPhone(request.getPhone())
                .orElseThrow(() -> new RuntimeException("User not found"));

        loginSessionService.recordLogin(customer.getId(), ipAddress, deviceInfo);

        String token = jwtTokenProvider.generateToken(request.getPhone());
        return new AuthResponse(token);
    }

    private String generateOtp() {
        Random random = new Random();
        int otp = 100_000 + random.nextInt(900_000);
        return String.valueOf(otp);
    }


}
