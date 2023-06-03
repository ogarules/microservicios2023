package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Card;
import com.example.demo.repositories.CardRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CardService implements ICardService {

    @Autowired
    CardRepository repository;

    @Override
    public Card getCardById(Long id) {
        log.info("Getting card {}", id);

        Card card = repository.findById(id).orElseThrow();

        log.info("Card =>", card.getCardNumber());

        return card;
    }

    @Override
    public Iterable<Card> getAllCardsa() {
        log.info("Getting all cards...");

        return this.repository.findAll();
    }

    @Override
    public Card addCard(Card card) {
        log.info("Inserting new card {}", card.getCardNumber());

        Card cardDb = this.repository.save(card);

        log.info("Card id {} for card {}", cardDb.getId(), cardDb.getCardNumber());

        return cardDb;
    }

    @Override
    public Card updateCard(Long id, Card card) {
        log.info("Updating card {}", id);

        Card cardDb = repository.findById(id).orElseThrow();
        cardDb.setCardExpiration(card.getCardExpiration());
        cardDb.setCardName(card.getCardName());
        cardDb.setCardNumber(card.getCardNumber());

        return repository.save(cardDb);
    }

    @Override
    public Card deleteCard(Long id) {
        log.info("Deleting card {}", id);

        Card card = this.repository.findById(id).orElseThrow();

        this.repository.deleteById(id);

        return card;
    }
    
}
