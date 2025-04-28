package com.banking.banking_system.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionHistoryRequest {
    private Long accountId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String type;
    private String status;
}