package com.banking.banking_system.service.impl;

import com.banking.banking_system.dto.request.TransactionHistoryRequest;
import com.banking.banking_system.dto.request.DepositWithdrawRequest;
import com.banking.banking_system.dto.request.TransferRequest;
import com.banking.banking_system.entity.Account;
import com.banking.banking_system.entity.AuditLog;
import com.banking.banking_system.entity.Transaction;
import com.banking.banking_system.repository.AccountRepository;
import com.banking.banking_system.repository.AuditLogRepository;
import com.banking.banking_system.repository.TransactionRepository;
import com.banking.banking_system.service.inter.AuditLogService;
import com.banking.banking_system.service.inter.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RequiredArgsConstructor

@Service
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final AuditLogService auditLogService;

    // Chuyển tiền giữa các tài khoản
    public void transfer(TransferRequest request, HttpServletRequest httpServletRequest) {
        Account fromAccount = findAccountById(request.getFromAccountId());
        Account toAccount = findAccountById(request.getToAccountId());

        if (fromAccount.getBalance().compareTo(request.getAmount()) < 0) {
            throw new IllegalArgumentException("Insufficient balance!");
        }

        // Update balances
        fromAccount.setBalance(fromAccount.getBalance().subtract(request.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(request.getAmount()));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        // Save transaction
        Transaction transaction = new Transaction();
        transaction.setTransactionCode(UUID.randomUUID().toString());
        transaction.setFromAccountId(fromAccount.getId());
        transaction.setToAccountId(toAccount.getId());
        transaction.setType("TRANSFER");
        transaction.setAmount(request.getAmount());
        transaction.setDescription(request.getDescription());
        transaction.setStatus("SUCCESS");
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);

        // Audit log
        String ipAddress = httpServletRequest.getRemoteAddr();
        auditLogService.logAction(fromAccount.getCustomerId(), "TRANSFER", "Transferred to account: " + toAccount.getAccountNumber(), ipAddress);
    }

    @Override
    public void deposit(DepositWithdrawRequest request, HttpServletRequest httpServletRequest) {
        Account account = findAccountById(request.getAccountId());

        account.setBalance(account.getBalance().add(request.getAmount()));
        accountRepository.save(account);

        // Save transaction
        Transaction transaction = new Transaction();
        transaction.setTransactionCode(UUID.randomUUID().toString());
        transaction.setFromAccountId(null);
        transaction.setToAccountId(account.getId());
        transaction.setType("DEPOSIT");
        transaction.setAmount(request.getAmount());
        transaction.setDescription(request.getDescription());
        transaction.setStatus("SUCCESS");
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);
        String ipAddress = httpServletRequest.getRemoteAddr();
        auditLogService.logAction(account.getCustomerId(), "DEPOSIT", "Deposited amount: " + request.getAmount(), ipAddress);
    }

    @Override
    public void withdraw(DepositWithdrawRequest request, HttpServletRequest httpServletRequest) {
        Account account = findAccountById(request.getAccountId());

        if (account.getBalance().compareTo(request.getAmount()) < 0) {
            throw new IllegalArgumentException("Insufficient balance!");
        }

        account.setBalance(account.getBalance().subtract(request.getAmount()));
        accountRepository.save(account);

        // Save transaction
        Transaction transaction = new Transaction();
        transaction.setTransactionCode(UUID.randomUUID().toString());
        transaction.setFromAccountId(account.getId());
        transaction.setToAccountId(null);
        transaction.setType("WITHDRAWAL");
        transaction.setAmount(request.getAmount());
        transaction.setDescription(request.getDescription());
        transaction.setStatus("SUCCESS");
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);
        String ipAddress = httpServletRequest.getRemoteAddr();
        auditLogService.logAction(account.getCustomerId(), "WITHDRAWAL", "Withdrawn amount: " + request.getAmount(), ipAddress);
    }

    @Override
    public List<Transaction> getTransactionHistory(TransactionHistoryRequest request) {
        return transactionRepository.findTransactionsByFilters(
                request.getAccountId(), request.getStartDate(), request.getEndDate(), request.getType(), request.getStatus()
        );
    }
    private Account findAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new NoSuchElementException("Account not found with id: " + accountId));
    }
}