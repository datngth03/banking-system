package com.banking.banking_system.service.impl;

import com.banking.banking_system.dto.request.AddBeneficiaryRequest;
import com.banking.banking_system.dto.request.RemoveBeneficiaryRequest;
import com.banking.banking_system.entity.Beneficiary;
import com.banking.banking_system.repository.BeneficiaryRepository;
import com.banking.banking_system.service.inter.BeneficiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BeneficiaryServiceImpl implements BeneficiaryService {

    private final BeneficiaryRepository beneficiaryRepository;

    @Override
    public Beneficiary addBeneficiary(AddBeneficiaryRequest request) {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setCustomerId(request.getCustomerId());
        beneficiary.setBeneficiaryName(request.getBeneficiaryName());
        beneficiary.setBeneficiaryAccountId(request.getBeneficiaryAccountId());
        beneficiary.setBankName(request.getBankName());
        beneficiary.setBranchCode(request.getBranchCode());
        beneficiary.setCreatedAt(LocalDateTime.now());
        beneficiary.setUpdatedAt(LocalDateTime.now());

        return beneficiaryRepository.save(beneficiary);
    }

    @Override
    public void deleteBeneficiary(Long beneficiaryId) {
        beneficiaryRepository.deleteById(beneficiaryId);
    }

    @Override
    public List<Beneficiary> getBeneficiaries(Long customerId) {
        return beneficiaryRepository.findByCustomerId(customerId);
    }
}
