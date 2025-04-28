package com.banking.banking_system.dto.request;

import java.time.LocalDate;

public record CustomerRequest(
        String status,
        String fullName,
        String phone,
        LocalDate birthDate,
        String address
) {}
