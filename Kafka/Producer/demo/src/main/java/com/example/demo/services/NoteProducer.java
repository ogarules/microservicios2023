package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.models.Note;

@Service
public class NoteProducer {
    
    @Autowired
    KafkaTemplate<String, Note> kafkaTemplate;

    public void sendNote(Note note){

        this.kafkaTemplate.send("notes", java.util.UUID.randomUUID().toString(), note);
    }
}
