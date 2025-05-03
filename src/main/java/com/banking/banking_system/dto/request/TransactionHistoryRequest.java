package com.banking.banking_system.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TransactionHistoryRequest {
    @NotNull(message = "Account ID must not be null")
    private Long accountId;

    @NotNull(message = "Start date must not be null")
    @PastOrPresent(message = "Start date cannot be in the future")
    private LocalDate startDate;

    @NotNull(message = "End date must not be null")
    @PastOrPresent(message = "End date cannot be in the future")
    private LocalDate endDate;

    @Size(max = 20, message = "Type must be at most 20 characters")
    private String type;

    @Size(max = 20, message = "Status must be at most 20 characters")
    private String status;
}
