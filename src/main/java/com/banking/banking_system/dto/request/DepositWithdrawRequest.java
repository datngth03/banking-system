package com.banking.banking_system.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositWithdrawRequest {
    @NotNull(message = "Account ID must not be null")
    private Long accountId;

    @NotNull(message = "Amount must not be null")
    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;

    private String description; // optional
}