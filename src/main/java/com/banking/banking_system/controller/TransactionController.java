package com.banking.banking_system.controller;

import com.banking.banking_system.dto.request.TransactionHistoryRequest;
import com.banking.banking_system.dto.request.DepositWithdrawRequest;
import com.banking.banking_system.dto.request.TransferRequest;
import com.banking.banking_system.dto.response.ResponseDto;
import com.banking.banking_system.entity.Transaction;
import com.banking.banking_system.service.inter.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<ResponseDto> transfer(@RequestBody @Valid TransferRequest request, HttpServletRequest httpServletRequest) {
        transactionService.transfer(request, httpServletRequest);
        return ResponseEntity.ok(new ResponseDto("SUCCESS", "Transfer completed successfully"));
    }

    @PostMapping("/deposit")
    public ResponseEntity<ResponseDto> deposit(@RequestBody @Valid DepositWithdrawRequest request, HttpServletRequest httpServletRequest) {
        transactionService.deposit(request, httpServletRequest);
        return ResponseEntity.ok(new ResponseDto("SUCCESS", "Deposit completed successfully"));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ResponseDto> withdraw(@RequestBody @Valid DepositWithdrawRequest request, HttpServletRequest httpServletRequest) {
        transactionService.withdraw(request, httpServletRequest);
        return ResponseEntity.ok(new ResponseDto("SUCCESS", "Withdrawal completed successfully"));
    }

    @GetMapping("/history")
    public ResponseEntity<?> getTransactionHistory(@RequestBody TransactionHistoryRequest request) {
        List<Transaction> transactions = transactionService.getTransactionHistory(request);
        if (transactions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No transactions found");
        }
        return ResponseEntity.ok(transactions);
    }
}