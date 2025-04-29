package com.banking.banking_system.controller;

import com.banking.banking_system.dto.response.ResponseDto;
import com.banking.banking_system.service.impl.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/{cardId}/lock")
    public ResponseEntity<ResponseDto> lockCard(@PathVariable Long cardId) {
        cardService.lockCard(cardId);
        return ResponseEntity.ok(new ResponseDto("SUCCESS", "Card locked successfully"));
    }
}
