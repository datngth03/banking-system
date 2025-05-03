package com.banking.banking_system.dto.request;
import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class OtpRequest {
    @NotBlank(message = "Phone number is required")
    private String phone;

    @NotBlank(message = "OTP is required")
    private String otp;
}
