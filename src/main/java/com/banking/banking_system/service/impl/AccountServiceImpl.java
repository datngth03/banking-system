package com.banking.banking_system.service.impl;

import com.banking.banking_system.dto.request.ChangeAccountStatusRequest;
import com.banking.banking_system.dto.request.CreateAccountRequest;
import com.banking.banking_system.entity.Account;
import com.banking.banking_system.entity.Card;
import com.banking.banking_system.repository.AccountRepository;
import com.banking.banking_system.repository.CardRepository;
import com.banking.banking_system.service.inter.AccountService;
//import jakarta.transaction.Transactional;
import com.banking.banking_system.service.inter.AuditLogService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private  AuditLogService auditLogService;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CardRepository cardRepository;



    public Account createAccount(CreateAccountRequest request) {
        Account account = new Account();
        account.setCustomerId(request.getCustomerId());
        account.setAccountNumber(generateAccountNumber());
        account.setAccountType(request.getAccountType());
        account.setBalance(BigDecimal.ZERO);  // Số dư mặc định là 0
        account.setStatus("ACTIVE");
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());

        Account savedAccount = accountRepository.save(account);

        Card card = new Card();
        card.setAccountId(savedAccount.getId());
        card.setCardType(request.getCardType());
        card.setCardNumber(generateCardNumber());
        card.setCardIdentifier(generateCardIdentifier());
        card.setExpiryDate(generateExpiryDate());
        card.setCvv(generateCVV());
        card.setStatus("ACTIVE");
        card.setCreatedAt(LocalDateTime.now());
        card.setUpdatedAt(LocalDateTime.now());
        cardRepository.save(card);

        return savedAccount;
    }
    private String generateAccountNumber() {
        return "ACC" + UUID.randomUUID().toString().substring(0, 10);
    }

    private String generateCardNumber() {
        return "CARD" + UUID.randomUUID().toString().substring(0, 16);
    }

    private String generateCardIdentifier() {
        return UUID.randomUUID().toString().substring(0, 10);
    }

    private LocalDate generateExpiryDate() {
        return LocalDate.now().plusYears(3);
    }

    private String generateCVV() {
        return String.format("%03d", (int)(Math.random() * 1000));
    }

    public BigDecimal getBalance(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return account.getBalance();
    }

    public Account changeAccountStatus(ChangeAccountStatusRequest request, HttpServletRequest servletRequest) {
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

        String ipAddress = servletRequest.getHeader("X-Forwarded-For");
        // ✅ Ghi log thay đổi trạng thái tài khoản
        auditLogService.log(
                request.getCustomerId(),
                "CHANGE_ACCOUNT_STATUS",
                "Changed status of account " + account.getAccountNumber() + " to " + request.getAction(),
                ipAddress
        );
        return accountRepository.save(account);
    }
}
