package com.banking.banking_system.repository;

import com.banking.banking_system.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    @Query("SELECT c FROM Card c WHERE c.expiryDate < :expiryDate AND c.status <> :status")
    List<Card> findByExpiryDateBeforeAndStatusNot(@Param("status") String status, @Param("expiryDate") LocalDate expiryDate);
}