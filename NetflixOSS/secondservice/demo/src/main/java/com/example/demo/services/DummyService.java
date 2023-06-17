package com.example.demo.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient(name="first-service")
public interface DummyService {
    
    @GetMapping("dummy")
    @CircuitBreaker(name = "service1", fallbackMethod = "fallback")
    public String getDummyFromFirstService();

    default String fallback(Exception e){
        return "error";
    }
}
