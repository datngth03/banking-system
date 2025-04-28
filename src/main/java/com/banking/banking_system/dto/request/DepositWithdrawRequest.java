package com.banking.banking_system.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositWithdrawRequest {
    private Long accountId;
    private BigDecimal amount;
    private String description;
}