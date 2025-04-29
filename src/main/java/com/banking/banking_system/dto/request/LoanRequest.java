package com.banking.banking_system.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class LoanRequest {
    private Long customerId;
    private String loanType;
    private BigDecimal amount;
    private Float interestRate;
    private LocalDate startDate;
    private LocalDate endDate;
}
