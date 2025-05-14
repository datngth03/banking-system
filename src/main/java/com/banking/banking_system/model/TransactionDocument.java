package com.banking.banking_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "bk_transactions")  // Đặt tên index trong Elasticsearch
public class TransactionDocument {

    @Id
    private String transactionCode;

    private Long fromAccountId;
    private Long toAccountId;
    private String type;
    private String description;
    private String status;
    private LocalDateTime timestamp;
    private Double amount;

}