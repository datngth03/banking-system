package com.banking.banking_system.dto.request;

import lombok.Data;

@Data
public class AddBeneficiaryRequest {
    private Long customerId;
    private String beneficiaryName;
    private Long beneficiaryAccountId;
    private String bankName;
    private String branchCode;
}
