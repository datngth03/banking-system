package com.banking.banking_system.dto.response;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor

public class LoanResponse {
    private Long id;
    private Long customerId;
    private String loanType;
    private BigDecimal amount;
    private Float interestRate;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
}
//
