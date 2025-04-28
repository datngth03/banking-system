package com.banking.banking_system.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordUtil {
        public static String hashPassword(String plainPassword) {
            return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        }
    }
