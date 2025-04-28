package com.banking.banking_system.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAccountRequest {
    @NotNull
    private Long customerId;

    @NotNull
    private String accountType;

    private BigDecimal initialBalance = BigDecimal.valueOf(0.000);
}

