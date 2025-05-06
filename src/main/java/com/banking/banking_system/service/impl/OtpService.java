package com.banking.banking_system.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final StringRedisTemplate redisTemplate;
    private final Duration otpTtl = Duration.ofMinutes(2);
    private final int MAX_ATTEMPTS = 10;

    public void saveOtp(String phone, String otp) {
        redisTemplate.opsForValue().set("otp:" + phone, otp, otpTtl);
        redisTemplate.delete("otp:fail:" + phone);
    }

    public boolean verifyOtp(String phone, String inputOtp) {
        String key = "otp:" + phone;
        String storedOtp = redisTemplate.opsForValue().get(key);

        if (storedOtp == null) return false;
        if (!storedOtp.equals(inputOtp)) {
            incrementFail(phone);
            return false;
        }

        redisTemplate.delete(key);
        redisTemplate.delete("otp:fail:" + phone);
        return true;
    }

    private void incrementFail(String phone) {
        String failKey = "otp:fail:" + phone;
        redisTemplate.opsForValue().increment(failKey);
        redisTemplate.expire(failKey, Duration.ofMinutes(5));
    }

    public boolean isBlocked(String phone) {
        String key = "otp:fail:" + phone;
        String val = redisTemplate.opsForValue().get(key);
        return val != null && Integer.parseInt(val) >= MAX_ATTEMPTS;
    }
}
