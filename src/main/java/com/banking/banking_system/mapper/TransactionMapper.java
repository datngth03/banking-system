package com.banking.banking_system.mapper;

import com.banking.banking_system.entity.Transaction;
import com.banking.banking_system.model.TransactionDocument;

public class TransactionMapper {

    public static TransactionDocument toElasticsearchDocument(Transaction transaction) {
        TransactionDocument doc = new TransactionDocument();
        doc.setTransactionCode(transaction.getTransactionCode());
        doc.setFromAccountId(transaction.getFromAccountId());
        doc.setToAccountId(transaction.getToAccountId());
        doc.setType(transaction.getType());
        doc.setAmount(transaction.getAmount().doubleValue()); // đổi BigDecimal -> Double
        doc.setDescription(transaction.getDescription());
        doc.setStatus(transaction.getStatus());
        doc.setTimestamp(transaction.getTimestamp());
        return doc;
    }
}