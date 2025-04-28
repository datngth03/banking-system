package com.banking.banking_system.service.impl;

import com.banking.banking_system.dto.request.OtpRequest;
import com.banking.banking_system.dto.request.RegisterRequest;
import com.banking.banking_system.dto.response.AuthResponse;
import com.banking.banking_system.entity.Customer;
import com.banking.banking_system.exception.CustomException;
import com.banking.banking_system.repository.CustomerRepository;
import com.banking.banking_system.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
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
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmailService emailService;


    public AuthResponse register(RegisterRequest request) {
        if (customerRepository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }

        Customer customer = Customer.builder()
                .username(request.getUsername())
                .identityNumber(request.getIdentityNumber()) // Số CMND/CCCD
                .email(request.getEmail())
                .fullName(request.getFullName())
                .phone(request.getPhone())
                .address(request.getAddress())
                .dateOfBirth(request.getDateOfBirth())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .status("ACTIVE") // Khi đăng ký, mặc định ACTIVE
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        customerRepository.save(customer);


        String token = jwtTokenProvider.generateToken(customer.getUsername());
        return new AuthResponse(token);
    }



    public String login(RegisterRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String otp = generateOtp();
        OtpStore.saveOtp(request.getUsername(), otp);

        Customer customer = customerRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        emailService.sendOtpEmail(customer.getEmail(), otp);

        return "OTP code sent to your email!";
    }

    public AuthResponse verifyOtp(OtpRequest request) {
        String savedOtp = OtpStore.getOtp(request.getUsername());

        if (savedOtp == null) {
            throw new CustomException("OTP is invalid or expired!");
        }

        if (!savedOtp.equals(request.getOtp())) {
            throw new CustomException("Incorrect OTP entered!");
        }

        // Xóa OTP sau khi verify
        OtpStore.removeOtp(request.getUsername());

        String token = jwtTokenProvider.generateToken(request.getUsername());
        return new AuthResponse(token);
    }

    private String generateOtp() {
        Random random = new Random();
        int otp = 100_000 + random.nextInt(900_000);
        return String.valueOf(otp);
    }
}
