package com.banking.banking_system.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Table(name = "login_sessions")
@Data
public class LoginSession {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private String ipAddress;
    private String deviceInfo;
    private LocalDateTime loginTime;
}

