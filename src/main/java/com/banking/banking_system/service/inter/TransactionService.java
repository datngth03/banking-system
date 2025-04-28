package com.banking.banking_system.service.inter;

import com.banking.banking_system.dto.request.DepositWithdrawRequest;
import com.banking.banking_system.dto.request.TransactionHistoryRequest;
import com.banking.banking_system.dto.request.TransferRequest;
import com.banking.banking_system.entity.Transaction;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface TransactionService {
    void transfer(TransferRequest request, HttpServletRequest httpServletRequest);
    void deposit(DepositWithdrawRequest request, HttpServletRequest httpServletRequest);
    void withdraw(DepositWithdrawRequest request, HttpServletRequest httpServletRequest);
    List<Transaction> getTransactionHistory(TransactionHistoryRequest request);
}
