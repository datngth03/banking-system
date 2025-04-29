package com.banking.banking_system.service.impl;

import com.banking.banking_system.entity.Card;
import com.banking.banking_system.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public void lockCard(Long cardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));

        card.setStatus("LOCKED");
        card.setUpdatedAt(LocalDateTime.now());
        cardRepository.save(card);
    }

    @Scheduled(cron = "0 0 0 * * ?")  // Lên lịch kiểm tra mỗi ngày vào lúc 12:00 AM
    public void expireCards() {
        List<Card> expiredCards = cardRepository.findByExpiryDateBeforeAndStatusNot("EXPIRED", LocalDate.now());

        for (Card card : expiredCards) {
            card.setStatus("EXPIRED");
            card.setUpdatedAt(LocalDateTime.now());
            cardRepository.save(card);
        }
    }
}
