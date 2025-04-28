package com.banking.banking_system.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    private Long fromAccountId;
    private Long toAccountId;
    private BigDecimal amount;
    private String description;
}