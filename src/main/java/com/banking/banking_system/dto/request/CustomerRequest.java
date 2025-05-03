package com.banking.banking_system.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {

    @NotBlank(message = "Identity number is required")
    @Size(min = 9, max = 12, message = "Identity number must be between 9 and 12 characters")
    private String identityNumber;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email format is invalid")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10,11}", message = "Phone number must be 10–11 digits")
    private String phone;

    @NotNull(message = "Birth date is required")
    private LocalDate birthDate;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Status is required")
    private String status;
}

