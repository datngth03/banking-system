package com.banking.banking_system.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Table(name = "notifications")
@Data
public class Notification {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private String title;
    private String content;
    private Boolean isRead = false;

    private LocalDateTime createdAt;
}

