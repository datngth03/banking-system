package com.banking.banking_system.service.impl;

import com.banking.banking_system.dto.request.ChangeAccountStatusRequest;
import com.banking.banking_system.dto.request.CreateAccountRequest;
import com.banking.banking_system.entity.Account;
import com.banking.banking_system.repository.AccountRepository;
import com.banking.banking_system.service.inter.AccountService;
//import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(CreateAccountRequest request) {
        Account account = new Account();
        account.setCustomerId(request.getCustomerId());
        account.setAccountType(request.getAccountType());
        account.setBalance(request.getInitialBalance());
        account.setStatus("ACTIVE");
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());
        account.setAccountNumber(generateAccountNumber());

        return accountRepository.save(account);
    }

    public BigDecimal getBalance(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return account.getBalance();
    }

    public Account changeAccountStatus(ChangeAccountStatusRequest request) {
        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if ("lock".equalsIgnoreCase(request.getAction())) {
            account.setStatus("LOCKED");
        } else if ("unlock".equalsIgnoreCase(request.getAction())) {
            account.setStatus("ACTIVE");
        } else {
            throw new RuntimeException("Invalid action");
        }

        account.setUpdatedAt(LocalDateTime.now());
        return accountRepository.save(account);
    }

    private String generateAccountNumber() {
        return "AC" + System.currentTimeMillis();
    }
}
