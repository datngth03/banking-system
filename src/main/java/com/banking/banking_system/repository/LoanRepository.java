package com.banking.banking_system.repository;

import com.banking.banking_system.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Query("SELECT l FROM Loan l WHERE l.customerId = :customerId AND l.status = :status")
    List<Loan> findByCustomerIdAndStatus(@Param("customerId") Long customerId, @Param("status") String status);
}
