package com.banking.banking_system.controller;

import com.banking.banking_system.dto.CustomerDto;
import com.banking.banking_system.dto.request.ChangePasswordRequest;
import com.banking.banking_system.dto.request.CustomerRequest;
import com.banking.banking_system.dto.response.ResponseDto;
import com.banking.banking_system.service.inter.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest request) {
        return ResponseEntity.ok(customerService.updateCustomerById(id, request));
    }

    @PutMapping("/{id}/change-password")
    public ResponseDto changePassword(
            @PathVariable Long id,
            @RequestBody ChangePasswordRequest request) {
        return customerService.changePassword(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseDto deleteCustomerById(@PathVariable Long id) {
        return customerService.deleteCustomerById(id);
    }


}

