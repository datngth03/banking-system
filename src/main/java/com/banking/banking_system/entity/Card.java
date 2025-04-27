package com.banking.banking_system.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "cards")
public class Card {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne @JoinColumn(name = "account_id", nullable = false)
    private Account account;

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

