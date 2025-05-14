package com.banking.banking_system.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanPaymentSchedule {

    @NotNull(message = "Due date must not be null")
    private LocalDate dueDate;

    @NotNull(message = "EMI amount must not be null")
    @Positive(message = "EMI amount must be greater than zero")
    private BigDecimal emiAmount;
}
