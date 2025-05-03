package com.banking.banking_system.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransferRequest {
    @NotNull(message = "Source account ID must not be null")
    private Long fromAccountId;

    @NotNull(message = "Destination account ID must not be null")
    private Long toAccountId;

    @NotNull(message = "Amount must not be null")
    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;

    @Size(max = 255, message = "Description must be at most 255 characters")
    private String description;
}
