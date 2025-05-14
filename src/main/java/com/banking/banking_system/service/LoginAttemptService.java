package com.banking.banking_system.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class LoginAttemptService {

    private final StringRedisTemplate redisTemplate;
    private static final int MAX_ATTEMPTS = 5;
    private static final Duration BLOCK_TIME = Duration.ofMinutes(1);

    public LoginAttemptService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void loginFailed(String username) {
        String key = "login_fail:" + username;
        String attemptsStr = redisTemplate.opsForValue().get(key);
        int attempts = (attemptsStr != null) ? Integer.parseInt(attemptsStr) : 0;
        redisTemplate.opsForValue().set(key, String.valueOf(attempts + 1), BLOCK_TIME);
    }

    public boolean isBlocked(String username) {
        String attemptsStr = redisTemplate.opsForValue().get("login_fail:" + username);
        int attempts = (attemptsStr != null) ? Integer.parseInt(attemptsStr) : 0;
        return attempts >= MAX_ATTEMPTS;
    }

    public void loginSucceeded(String username) {
        redisTemplate.delete("login_fail:" + username);
    }
}
