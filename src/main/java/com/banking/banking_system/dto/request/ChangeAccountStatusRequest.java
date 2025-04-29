package com.banking.banking_system.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class ChangeAccountStatusRequest {
    @NotNull
    private Long customerId;
    @NotNull
    private Long accountId;

    @NotNull
    private String action; // "lock" hoặc "unlock"
}
