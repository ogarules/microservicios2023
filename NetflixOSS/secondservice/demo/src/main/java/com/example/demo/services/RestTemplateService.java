package com.example.demo.services;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class RestTemplateService {
    
    //@Autowired
    private final RestTemplate template;

    public RestTemplateService(RestTemplate template){
        this.template=template;
    }

    @CircuitBreaker(name="botService", fallbackMethod = "fallBackTemplate")
    public String getTemplateResponse(){
        URI uri = URI.create("http://localhost:8091/dummy");

        String response = this.template.getForObject(uri, String.class);

        return response;
    }

    public String fallBackTemplate(Exception e){
        return "Errrroooorrrr";
    }
}
