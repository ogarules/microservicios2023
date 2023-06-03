package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Card;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
    
}
