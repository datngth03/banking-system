package com.banking.banking_system.service.impl;

import com.banking.banking_system.dto.CustomerDto;
import com.banking.banking_system.dto.request.ChangePasswordRequest;
import com.banking.banking_system.dto.request.CustomerRequest;
import com.banking.banking_system.dto.response.ResponseDto;
import com.banking.banking_system.entity.Customer;
import com.banking.banking_system.exception.CustomException;
import com.banking.banking_system.mapper.CustomerMapper;
import com.banking.banking_system.service.inter.CustomerService;
import com.banking.banking_system.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    public CustomerDto getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(CustomerMapper::toDto)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));
    }

    @Override
    public CustomerDto createCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
                .identityNumber(request.getIdentityNumber())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .username(request.getFullName())
                .passwordHash(passwordEncoder.encode("defaultPass"))
                .status("ACTIVE")
                .dateOfBirth(request.getBirthDate())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Customer saved = customerRepository.save(customer);
        return CustomerMapper.toDto(saved);
    }


    @Override
    public CustomerDto updateCustomerById(Long id, CustomerRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomException("Customer not found with id: " + id));

        customer.setEmail(request.getStatus());
        customer.setFullName(request.getFullName());
        customer.setPhone(request.getPhone());
        customer.setDateOfBirth(request.getBirthDate());
        customer.setAddress(request.getAddress());

        Customer updatedCustomer = customerRepository.save(customer);
        return CustomerMapper.toDto(updatedCustomer);
    }

    @Override
    public ResponseDto changePassword(Long id, ChangePasswordRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomException("Customer not found with id: " + id));

        if (!passwordEncoder.matches(request.getOldPassword(), customer.getPasswordHash())) {
            throw new CustomException("Old password is incorrect.");
        }

        customer.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));
        customerRepository.save(customer);

        return new ResponseDto("SUCCESS", "Password changed successfully");
    }

    @Override
    public ResponseDto deleteCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomException("Customer not found with id: " + id));

        customer.setStatus("INACTIVE");
        customerRepository.save(customer);

        return new ResponseDto("SUCCESS", "Customer account deactivated successfully.");
    }

}

