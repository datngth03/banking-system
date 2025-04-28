package com.banking.banking_system.service.impl;


import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

public class OtpStore {
    private static final ConcurrentHashMap<String, OtpEntry> otpMap = new ConcurrentHashMap<>();
    private static final int EXPIRE_MINUTES = 5; // OTP có hiệu lực 5 phút

    public static void saveOtp(String username, String otp) {
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(EXPIRE_MINUTES);
        otpMap.put(username, new OtpEntry(otp, expiryTime));
    }

    public static String getOtp(String username) {
        OtpEntry entry = otpMap.get(username);
        if (entry == null) return null;
        if (entry.getExpiryTime().isBefore(LocalDateTime.now())) {
            otpMap.remove(username);
            return null; // OTP hết hạn rồi
        }
        return entry.getOtp();
    }

    public static void removeOtp(String username) {
        otpMap.remove(username);
    }

    private static class OtpEntry {
        private final String otp;
        private final LocalDateTime expiryTime;

        public OtpEntry(String otp, LocalDateTime expiryTime) {
            this.otp = otp;
            this.expiryTime = expiryTime;
        }

        public String getOtp() {
            return otp;
        }

        public LocalDateTime getExpiryTime() {
            return expiryTime;
        }
    }
}
