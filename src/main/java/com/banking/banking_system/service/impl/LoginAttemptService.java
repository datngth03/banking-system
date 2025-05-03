package com.banking.banking_system.service.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class LoginAttemptService {

    private final RedisTemplate<String, Integer> redisTemplate;
    private static final int MAX_ATTEMPTS = 5;
    private static final Duration BLOCK_TIME = Duration.ofMinutes(1);

    public LoginAttemptService(RedisTemplate<String, Integer> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void loginFailed(String username) {
        String key = "login_fail:" + username;
        Integer attempts = redisTemplate.opsForValue().get(key);
        if (attempts == null) {
            redisTemplate.opsForValue().set(key, 1, BLOCK_TIME);
        } else {
            redisTemplate.opsForValue().set(key, attempts + 1, BLOCK_TIME);
        }
    }

    public boolean isBlocked(String username) {
        Integer attempts = redisTemplate.opsForValue().get("login_fail:" + username);
        return attempts != null && attempts >= MAX_ATTEMPTS;
    }

    public void loginSucceeded(String username) {
        redisTemplate.delete("login_fail:" + username);
    }
}

