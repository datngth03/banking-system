package com.banking.banking_system.controller;

import com.banking.banking_system.dto.LoanPaymentSchedule;
import com.banking.banking_system.dto.request.LoanRequest;
import com.banking.banking_system.dto.response.LoanResponse;
import com.banking.banking_system.dto.response.ResponseDto;
import com.banking.banking_system.entity.Loan;
import com.banking.banking_system.service.impl.LoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class LoanController {

    @Autowired
    private LoanService loanService;

    // Tạo hồ sơ vay
    @PostMapping("/create")
    public ResponseEntity<LoanResponse> createLoan(@RequestBody @Valid LoanRequest loanRequest) {
        Loan loan = loanService.createLoan(loanRequest);
        LoanResponse loanResponse = new LoanResponse(loan.getId(), loan.getCustomerId(), loan.getLoanType(), loan.getAmount(),
                loan.getInterestRate(), loan.getStartDate(), loan.getEndDate(), loan.getStatus());

        return ResponseEntity.status(HttpStatus.CREATED).body(loanResponse);
    }

    @GetMapping("/active-loans/{customerId}")
    public ResponseEntity<List<Loan>> getActiveLoans(@PathVariable Long customerId) {
        List<Loan> activeLoans = loanService.getActiveLoans(customerId);
        if (activeLoans.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(activeLoans);
    }

    @GetMapping("/payment-schedule/{loanId}")
    public ResponseEntity<List<LoanPaymentSchedule>> getPaymentSchedule(@PathVariable Long loanId) {
        Loan loan = loanService.getLoanById(loanId);
        if (loan == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<LoanPaymentSchedule> schedules = loanService.generatePaymentSchedule(loan);
        return ResponseEntity.ok(schedules);
    }
}
