package com.banking.banking_system.util;

import java.util.Random;

public class OTPUtil {
    public static String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

}
