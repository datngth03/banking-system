package com.banking.banking_system.repository;

import com.banking.banking_system.model.TransactionDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TransactionSearchRepository extends ElasticsearchRepository<TransactionDocument, String> {
}