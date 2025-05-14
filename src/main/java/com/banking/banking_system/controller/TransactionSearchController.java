package com.banking.banking_system.controller;

import com.banking.banking_system.model.TransactionDocument;
import com.banking.banking_system.service.TransactionSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/search")
public class TransactionSearchController {

    @Autowired
    private TransactionSearchService service;

    @PostMapping
    public ResponseEntity<?> index(@RequestBody TransactionDocument doc) {
        return ResponseEntity.ok(service.save(doc));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.findAll());
    }
}