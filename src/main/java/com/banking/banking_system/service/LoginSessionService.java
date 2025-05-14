package com.banking.banking_system.service;

import com.banking.banking_system.entity.LoginSession;
import com.banking.banking_system.repository.LoginSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoginSessionService {

    @Autowired
    private LoginSessionRepository loginSessionRepository;

    public void recordLogin(Long customerId, String ipAddress, String deviceInfo) {
        Optional<LoginSession> existingSession = loginSessionRepository.findByCustomerIdAndActiveTrue(customerId);

        if (existingSession.isPresent()) {
            LoginSession session = existingSession.get();
            session.setIpAddress(ipAddress);
            session.setDeviceInfo(deviceInfo);
            session.setLoginTime(LocalDateTime.now());
            loginSessionRepository.save(session);
        } else {
            LoginSession newSession = LoginSession.builder()
                    .customerId(customerId)
                    .ipAddress(ipAddress)
                    .deviceInfo(deviceInfo)
                    .loginTime(LocalDateTime.now())
                    .active(true)
                    .build();
            loginSessionRepository.save(newSession);
        }
    }

    public void logout(Long customerId) {
        Optional<LoginSession> existingSession = loginSessionRepository.findByCustomerIdAndActiveTrue(customerId);

        existingSession.ifPresent(session -> {
            session.setActive(false);
            loginSessionRepository.save(session);
        });
    }
}
