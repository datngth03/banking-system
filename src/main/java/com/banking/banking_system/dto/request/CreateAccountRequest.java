package com.banking.banking_system.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import jakarta.validation.constraints.*;


@Data
public class CreateAccountRequest {
    @NotNull(message = "Customer ID must not be null")
    private Long customerId;

    @NotBlank(message = "Account type must not be blank")
    private String accountType;

    @Positive(message = "Initial balance must be greater than zero")
    private BigDecimal initialBalance = BigDecimal.valueOf(0.000);

    private String cardType; // optional
}

