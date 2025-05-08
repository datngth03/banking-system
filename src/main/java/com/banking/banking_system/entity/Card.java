package com.banking.banking_system.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Table(name = "cards")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Card {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "card_number", unique = true)
    private String cardNumber;

    @Column(name = "card_identifier", unique = true)
    private String cardIdentifier;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;
    private String cvv;
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

