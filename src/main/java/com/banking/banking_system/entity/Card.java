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

    private Long accountId;

    private String cardType;

    @Column(unique = true)
    private String cardNumber;

    private String cardIdentifier;
    private LocalDate expiryDate;
    private String cvv;
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // getters/setters
}

