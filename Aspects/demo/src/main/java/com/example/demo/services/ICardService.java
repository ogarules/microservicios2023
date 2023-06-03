package com.example.demo.services;

import com.example.demo.models.Card;

public interface ICardService {
    
    public Card getCardById(Long id);
    public Iterable<Card> getAllCardsa();
    public Card addCard(Card card);
    public Card updateCard(Long id, Card card);
    public Card deleteCard(Long id);
}
