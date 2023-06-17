package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.DummyService;
import com.example.demo.services.RestTemplateService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("dummy")
@RequiredArgsConstructor
public class DummyController {
    
    @Value("S{server.port}")
    private String port;

    //@Autowired
    private final DummyService dummyService;

    //@Autowired
    private final RestTemplateService templateService;

    @GetMapping
    public String getDummy() {
        return this.dummyService.getDummyFromFirstService();
    }

    @GetMapping("template")
    public String getDummyFromTemplate() {
        return this.templateService.getTemplateResponse();
    }
    
    
}
