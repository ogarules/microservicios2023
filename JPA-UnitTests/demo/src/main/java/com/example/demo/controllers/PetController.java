package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Pet;
import com.example.demo.services.IPetService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/pets")
public class PetController {
    
    @Autowired
    private IPetService service;

    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable Long id) {
        log.info("Controller requesting pet {}", id);
        return this.service.getPetById(id);
    }

    @GetMapping
    public Iterable<Pet> getAllPets() {
        log.info("Controller requesting all pets");
        return this.service.getAllPets();
    }
    
    @PostMapping
    public ResponseEntity<Pet> addPet(@RequestBody Pet entity) {
        log.info("Controller adding pet {}", entity);

        Pet pet = this.service.addPet(entity);
        
        return ResponseEntity.ok(pet);
    }

    @PutMapping("/{id}")
    public Pet updatePet(@PathVariable Long id, @RequestBody Pet entity) {
        log.info("Controller updating pet {}", id);
        
        return this.service.updatePet(entity, id);
    }
    
    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Long id){
        this.service.deletePet(id);
    }
}