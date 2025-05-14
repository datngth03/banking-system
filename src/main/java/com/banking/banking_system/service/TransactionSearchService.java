package com.banking.banking_system.service;

import com.banking.banking_system.model.TransactionDocument;
import com.banking.banking_system.repository.TransactionSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionSearchService {

    @Autowired
    private TransactionSearchRepository repository;

    public TransactionDocument save(TransactionDocument doc) {
        return repository.save(doc);
    }

    public Iterable<TransactionDocument> findAll() {
        return repository.findAll();
    }
}
