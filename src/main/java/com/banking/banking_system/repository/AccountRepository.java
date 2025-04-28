package com.banking.banking_system.repository;


import com.banking.banking_system.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNumber(String accountNumber);  // Tìm kiếm tài khoản theo số tài khoản
}
