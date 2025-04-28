package com.banking.banking_system.repository;

import com.banking.banking_system.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE " +
            "(t.fromAccountId = :accountId OR t.toAccountId = :accountId) " +
            "AND (t.timestamp BETWEEN :startDate AND :endDate) " +
            "AND (:type IS NULL OR t.type = :type) " +
            "AND (:status IS NULL OR t.status = :status)")
    List<Transaction> findTransactionsByFilters(
            @Param("accountId") Long accountId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("type") String type,
            @Param("status") String status);
}