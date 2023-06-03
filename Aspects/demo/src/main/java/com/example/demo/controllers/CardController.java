package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Card;
import com.example.demo.services.ICardService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Slf4j
@RestController
@RequestMapping("/card")
public class CardController {
    
    @Autowired
    ICardService service;

    @GetMapping(value="/{id}")
    public Card getCardBy(@PathVariable Long id) {
        log.info("Retreving card...");

        return service.getCardById(id);
    }

    @GetMapping()
    public Iterable<Card> getAllCards() {
        log.info("Retreving all cards...");

        return service.getAllCardsa();
    }

    @PostMapping()
    public Card addCard(@RequestBody Card entity) {
        log.info("Adding card...");
        return service.addCard(entity);
    }
    
    @PutMapping(value="/{id}")
    public Card updateCard(@PathVariable Long id, @RequestBody Card entity) {
        log.info("Updating card...");
        
        return service.updateCard(id, entity);
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Long id){
        log.info("Deleting Card");

        service.deleteCard(id);
    }
    
}
    
