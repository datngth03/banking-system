package com.banking.banking_system.dto;

import java.time.LocalDate;

public record CustomerDto(
        Long id,
        String username,
        String email,
        String fullName,
        String phone,
        LocalDate dateOfBirth,
        String address,
        String status
) {}

