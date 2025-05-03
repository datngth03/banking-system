package com.banking.banking_system.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequest {
    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "Email is invalid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @Size(min = 9, max = 12, message = "Identity number must be between 9 and 12 digits")
    private String identityNumber;

    @NotNull(message = "Full name is required")
    private String fullName;

    @NotNull(message = "Phone number is required")
    private String phone;

    @NotNull(message = "Address is required")
    private String address;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;
}

