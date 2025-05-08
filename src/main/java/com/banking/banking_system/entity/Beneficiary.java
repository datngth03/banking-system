package com.banking.banking_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "beneficiaries")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Beneficiary {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "name")
    private String name;

    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "ifsc")
    private String ifsc;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

