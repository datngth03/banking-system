package com.banking.banking_system.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditLog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private String action;
    private LocalDateTime timestamp;
    private String ipAddress;
    private String notes;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    // getters/setters
}

