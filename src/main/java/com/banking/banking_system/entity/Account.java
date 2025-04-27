package com.banking.banking_system.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(unique = true, nullable = false)
    private String accountNumber;

    private String accountType;
    private BigDecimal balance;
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // getters/setters
}
