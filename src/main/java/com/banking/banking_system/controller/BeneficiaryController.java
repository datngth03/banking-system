package com.banking.banking_system.controller;

import com.banking.banking_system.dto.request.AddBeneficiaryRequest;
import com.banking.banking_system.entity.Beneficiary;
import com.banking.banking_system.service.inter.BeneficiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beneficiaries")
@RequiredArgsConstructor
public class BeneficiaryController {

    private final BeneficiaryService beneficiaryService;

    @PostMapping("/add")
    public ResponseEntity<Beneficiary> addBeneficiary(@RequestBody AddBeneficiaryRequest request) {
        Beneficiary beneficiary = beneficiaryService.addBeneficiary(request);
        return ResponseEntity.ok(beneficiary);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBeneficiary(@PathVariable Long id) {
        beneficiaryService.deleteBeneficiary(id);
        return ResponseEntity.ok("Beneficiary deleted successfully");
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Beneficiary>> getBeneficiaries(@PathVariable Long customerId) {
        List<Beneficiary> beneficiaries = beneficiaryService.getBeneficiaries(customerId);
        return ResponseEntity.ok(beneficiaries);
    }
}
