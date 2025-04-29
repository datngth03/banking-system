package com.banking.banking_system.dto.request;

import lombok.Data;

@Data
public class RemoveBeneficiaryRequest {
    private Long customerId;
    private Long beneficiaryAccountId;
}