package com.banking.banking_system.dto.request;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class ChangePasswordRequest {
    @NotBlank(message = "Old password must not be blank")
    private String oldPassword;

    @NotBlank(message = "New password must not be blank")
    @Size(min = 6, message = "New password must be at least 6 characters")
    private String newPassword;
}