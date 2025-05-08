package com.banking.banking_system.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "login_sessions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "ip_address", nullable = false)
    private String ipAddress;

    @Column(name = "device_info", nullable = false)
    private String deviceInfo;

    @Column(name = "login_time", nullable = false)
    private LocalDateTime loginTime;

    @Column(name = "loout_time", nullable = false)
    private LocalDateTime looutTime;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
