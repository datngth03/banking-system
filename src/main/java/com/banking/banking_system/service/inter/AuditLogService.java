package com.banking.banking_system.service.inter;

public interface AuditLogService {
    void log(Long customerId, String action, String notes, String ipAddress);
}