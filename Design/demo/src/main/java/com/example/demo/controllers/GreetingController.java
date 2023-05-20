package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Greeting;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/greeting")
@Api(tags={"Pet API"}, description = "API para mandar saludos de hola mundo")
public class GreetingController {
    
    @GetMapping
    @ApiOperation(value = "Obtiene todos los saludos", produces = "application/json", consumes = "application/json", protocols = "http,https", notes="Todos los saludos de hola mundo disponibles")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 404, message = "Saludos no encontrado")
    })
    public List<Greeting> getAllGreetings() {
        List<Greeting> greetings = new ArrayList<>();
        greetings.add(Greeting.builder().id(1L).message("Hello world !!!").location("CDMX").build());
        return greetings;
    }
    
    @PostMapping
    @ApiOperation(value = "Agrega un saludo", produces = "application/json", consumes = "application/json", protocols = "http,https", notes = "Agrega un nuevo greeeting de hola mundo")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success")
    })
    public Greeting addGreeting(@RequestBody Greeting greeting) {
        
        return greeting;
    }

    @GetMapping(value="/{message}")
    @ApiOperation(value = "Obtiene un saludo", produces = "application/json", consumes = "application/json", protocols = "http,https", notes = "Obtiene el saludo con el id proporcionado")
    @ApiResponses(value={
        @ApiResponse(code = 200, message = "Success")
    })
    public Greeting getGreeting(@PathVariable String message) {
        return Greeting.builder().id(1L).message(message).location("CDMX").build();
    }
    
    
}
