package com.banking.banking_system.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @NotNull(message = "Customer ID must not be null")
    private Long id;

    @NotBlank(message = "Username must not be blank")
    private String username;

    @Email(message = "Email must be a valid format")
    @NotBlank(message = "Email must not be blank")
    private String email;

    @NotBlank(message = "Full name must not be blank")
    @Size(min = 3, max = 100, message = "Full name must be between 3 and 100 characters")
    private String fullName;

    @NotBlank(message = "Phone number must not be blank")
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{9,15}$", message = "Phone number format is invalid")
    private String phone;

    @NotNull(message = "Date of birth must not be null")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Address must not be blank")
    private String address;

    @NotBlank(message = "Status must not be blank")
    private String status;
}
