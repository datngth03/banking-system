package com.banking.banking_system.service.impl;

import com.banking.banking_system.entity.AuditLog;
import com.banking.banking_system.repository.AuditLogRepository;
import com.banking.banking_system.service.inter.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    public void log(Long customerId, String action, String notes, String ipAddress) {
        AuditLog log = new AuditLog();
        log.setCustomerId(customerId);
        log.setAction(action);
        log.setNotes(notes);
        log.setIpAddress(ipAddress);
        log.setTimestamp(LocalDateTime.now());
        log.setCreatedAt(LocalDateTime.now());
        log.setUpdatedAt(LocalDateTime.now());
        auditLogRepository.save(log);
    }
}

