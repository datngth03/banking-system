package com.banking.banking_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanPaymentSchedule {
    private LocalDate dueDate;
    private BigDecimal emiAmount;

    // Constructor, getters, setters
}