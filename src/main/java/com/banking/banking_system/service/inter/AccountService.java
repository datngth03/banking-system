package com.banking.banking_system.service.inter;

import com.banking.banking_system.dto.request.ChangeAccountStatusRequest;
import com.banking.banking_system.dto.request.CreateAccountRequest;
import com.banking.banking_system.entity.Account;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;

public interface AccountService {
     Account createAccount(CreateAccountRequest request);
     BigDecimal getBalance(Long accountId);

     Account changeAccountStatus(ChangeAccountStatusRequest request, HttpServletRequest servletRequest);

}
