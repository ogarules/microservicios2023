package com.example.demo.services;

import com.example.demo.models.Greeting;

public interface IGreetingService {
    
    Greeting getGreeting(String message);

}
