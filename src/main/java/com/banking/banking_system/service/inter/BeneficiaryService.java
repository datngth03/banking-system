package com.banking.banking_system.service.inter;


import com.banking.banking_system.dto.request.AddBeneficiaryRequest;
import com.banking.banking_system.dto.request.RemoveBeneficiaryRequest;
import com.banking.banking_system.entity.Beneficiary;

import java.util.List;

public interface BeneficiaryService {
    Beneficiary addBeneficiary(AddBeneficiaryRequest request);
    void deleteBeneficiary(Long beneficiaryId);
    List<Beneficiary> getBeneficiaries(Long customerId);
}