package com.banking.banking_system.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "beneficiaries")
public class Beneficiary {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private String name;
    private String accountNumber;
    private String bankName;
    private String ifsc;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // getters/setters
}

