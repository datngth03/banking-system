package com.banking.banking_system.dto.request;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class ChangeAccountStatusRequest {
    @NotNull(message = "Customer ID is required")
    private Long customerId;
    @NotNull(message = "Account ID is required")
    private Long accountId;
    @NotNull(message = "Action must be lock or unlock")
    private String action;
}
