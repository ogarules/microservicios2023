package com.example.demo.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.models.Note;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConsumerService {
    
    @KafkaListener(topics = "notes", groupId = "group1")
    public void consume11(Note message){
        log.info("Mensaje consumido en el grupo 1.1 => {}", message);
    }

    @KafkaListener(topics = "notes", groupId = "group1")
    public void consume12(Note message){
        log.info("Mensaje consumido en el grupo 1.2 => {}", message);
    }

    @KafkaListener(topics = "notes", groupId = "group2")
    public void consumeotro(Note message){
        log.info("Mensaje consumido en el grupo 2 => {}", message);
    }
}
