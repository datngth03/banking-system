package com.banking.banking_system.service.inter;

import com.banking.banking_system.dto.CustomerDto;
import com.banking.banking_system.dto.request.ChangePasswordRequest;
import com.banking.banking_system.dto.request.CustomerRequest;
import com.banking.banking_system.dto.response.ResponseDto;

public interface CustomerService {
    CustomerDto getCustomerById(Long id);
    CustomerDto updateCustomerById(Long id, CustomerRequest request);
    ResponseDto changePassword(Long id, ChangePasswordRequest request);
    ResponseDto deleteCustomerById(Long id);
}

