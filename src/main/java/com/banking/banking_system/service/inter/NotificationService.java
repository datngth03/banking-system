package com.banking.banking_system.service.inter;

import com.banking.banking_system.entity.Notification;

import java.util.List;

public interface NotificationService {
    void sendNotification(Long customerId, String title, String content);
    List<Notification> getNotifications(Long customerId);
}