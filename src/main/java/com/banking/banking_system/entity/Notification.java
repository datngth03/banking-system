package com.banking.banking_system.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "notifications")
public class Notification {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private String type;
    private String title;
    private String message;
    private Boolean isRead;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // getters/setters
}

