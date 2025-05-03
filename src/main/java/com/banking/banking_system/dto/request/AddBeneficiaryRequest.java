package com.banking.banking_system.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AddBeneficiaryRequest {
    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotBlank(message = "Beneficiary name is required")
    @Size(max = 100, message = "Beneficiary name must be at most 100 characters")
    private String beneficiaryName;

    @NotNull(message = "Beneficiary account ID is required")
    private Long beneficiaryAccountId;

    @NotBlank(message = "Bank name is required")
    @Size(max = 100, message = "Bank name must be at most 100 characters")
    private String bankName;

    @NotBlank(message = "Branch code is required")
    @Size(max = 20, message = "Branch code must be at most 20 characters")
    private String branchCode;
}
