package com.banking.banking_system.mapper;

import com.banking.banking_system.dto.CustomerDto;
import com.banking.banking_system.dto.request.CustomerRequest;
import com.banking.banking_system.entity.Customer;
import com.banking.banking_system.util.PasswordUtil;

import java.time.LocalDateTime;

public class CustomerMapper {
    public static CustomerDto toDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getUsername(),
                customer.getEmail(),
                customer.getFullName(),
                customer.getPhone(),
                customer.getDateOfBirth(),
                customer.getAddress(),
                customer.getStatus()
        );
    }

    public static Customer toEntity(CustomerRequest request) {
        return Customer.builder()
                .fullName(request.fullName())
                .email(request.status())
                .phone(request.phone())
                .address(request.address())
                .dateOfBirth(request.birthDate())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}

