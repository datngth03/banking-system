package com.banking.banking_system.repository;

import com.banking.banking_system.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByUsername(String username);
    Optional<Customer> findByPhone(String phone);
    Optional<Customer> findByIdentityNumber(String username);
    boolean existsByPhone(String phone);
    boolean existsByIdentityNumber(String identityNumber);

}

