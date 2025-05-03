package com.banking.banking_system.repository;

import com.banking.banking_system.entity.LoginSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginSessionRepository extends JpaRepository<LoginSession, Long> {

    Optional<LoginSession> findByCustomerIdAndActiveTrue(Long customerId);
}