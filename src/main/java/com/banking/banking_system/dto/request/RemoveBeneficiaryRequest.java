package com.banking.banking_system.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class RemoveBeneficiaryRequest {
    @NotNull(message = "Customer ID must not be null")
    private Long customerId;

    @NotNull(message = "Beneficiary account ID must not be null")
    private Long beneficiaryAccountId;
}
