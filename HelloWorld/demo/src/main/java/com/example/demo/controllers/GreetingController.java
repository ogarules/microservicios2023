package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Greeting;
import com.example.demo.services.IGreetingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @Autowired
    private IGreetingService greetingService;
    
    @RequestMapping(value = "/hello/{message}", method = RequestMethod.GET)
    //@GetMapping(value="/hello")
    public Greeting getGreeting(@PathVariable String message){
        return greetingService.getGreeting(message);
    }

    @PostMapping(value="/hello")
    public Greeting postGreeting(@RequestBody Greeting entity) {
        
        entity.setMessage("Echo : " + entity.getMessage());
        
        return entity;
    }
    
    
    
}
