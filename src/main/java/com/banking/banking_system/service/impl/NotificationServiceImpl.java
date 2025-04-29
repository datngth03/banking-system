package com.banking.banking_system.service.impl;

import com.banking.banking_system.entity.Notification;
import com.banking.banking_system.repository.NotificationRepository;
import com.banking.banking_system.service.inter.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public void sendNotification(Long customerId, String title, String content) {
        Notification notification = new Notification();
        notification.setCustomerId(customerId);
        notification.setTitle(title);
        notification.setContent(content);
        notification.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getNotifications(Long customerId) {
        return notificationRepository.findByCustomerId(customerId);
    }
}