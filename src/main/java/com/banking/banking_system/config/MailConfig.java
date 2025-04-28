package com.banking.banking_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailConfig {
    @Bean
    public JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl(); // Và tự set host, port nếu muốn
    }
}
