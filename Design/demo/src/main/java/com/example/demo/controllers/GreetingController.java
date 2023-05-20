package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Greeting;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/greeting")
public class GreetingController {
    
    @GetMapping
    public List<Greeting> getAllGreetings() {
        List<Greeting> greetings = new ArrayList<>();
        greetings.add(Greeting.builder().id(1L).message("Hello world !!!").location("CDMX").build());
        return greetings;
    }
    
    @PostMapping
    public Greeting addGreeting(@RequestBody Greeting greeting) {
        
        return greeting;
    }

    @GetMapping(value="/{message}")
    public Greeting getGreeting(@PathVariable String message) {
        return Greeting.builder().id(1L).message(message).location("CDMX").build();
    }
    
    
}
