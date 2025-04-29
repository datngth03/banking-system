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

    private Long customerId;

    private String beneficiaryName;
    private Long beneficiaryAccountId;
    private String bankName; // hoặc Enum sau này
    private String branchCode; // hoặc swiftCo

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // getters/setters
}

