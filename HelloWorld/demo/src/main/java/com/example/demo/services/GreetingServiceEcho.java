package com.example.demo.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.models.Greeting;

@Service
public class GreetingServiceEcho implements IGreetingService {

    @Override
    public Greeting getGreeting(String message) {
        Greeting greeting = new Greeting();
        greeting.setMessage(message);

        return greeting;
    }
    
}
