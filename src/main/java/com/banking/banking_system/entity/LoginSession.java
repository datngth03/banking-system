package com.banking.banking_system.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "login_sessions")
public class LoginSession {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private LocalDateTime loginTime;
    private LocalDateTime logoutTime;

    private String ipAddress;
    private String deviceInfo;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // getters/setters
}

