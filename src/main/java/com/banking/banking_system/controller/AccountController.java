package com.banking.banking_system.controller;

import com.banking.banking_system.dto.request.ChangeAccountStatusRequest;
import com.banking.banking_system.dto.request.CreateAccountRequest;
import com.banking.banking_system.dto.request.TransactionHistoryRequest;
import com.banking.banking_system.dto.response.ResponseDto;
import com.banking.banking_system.entity.Account;
import com.banking.banking_system.entity.Transaction;
import com.banking.banking_system.service.inter.AccountService;
import com.banking.banking_system.service.inter.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private final TransactionService transactionService;
    private final AccountService accountService;
    public AccountController(TransactionService transactionService, AccountService accountService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
    }
//    private TransactionService transactionService;

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody CreateAccountRequest request) {
        Account account = accountService.createAccount(request);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/{accountId}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable Long accountId) {
        BigDecimal balance = accountService.getBalance(accountId);
        return ResponseEntity.ok(balance);
    }

    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<?> getAccountTransactionHistory(
            @PathVariable Long accountId,
            @RequestParam(value = "startDate", required = false) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) LocalDate endDate,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "status", required = false) String status) {

        TransactionHistoryRequest request = new TransactionHistoryRequest();
        request.setAccountId(accountId);
        request.setStartDate(startDate);
        request.setEndDate(endDate);
        request.setType(type);
        request.setStatus(status);

        List<Transaction> transactions = transactionService.getTransactionHistory(request);
        if (transactions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No transactions found for this account");
        }
        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/change-status")
    public ResponseEntity<ResponseDto> changeAccountStatus(@Valid @RequestBody ChangeAccountStatusRequest request) {
        Account account = accountService.changeAccountStatus(request);
        ResponseDto responseDto = new ResponseDto("SUCCESS", "Account status changed to: " + account.getStatus());
        return ResponseEntity.ok(responseDto);
    }
}
