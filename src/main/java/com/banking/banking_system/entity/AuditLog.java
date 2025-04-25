package com.banking.banking_system.entity;

@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private String action;
    private LocalDateTime timestamp;
    private String ipAddress;
    private String notes;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // getters/setters
}

