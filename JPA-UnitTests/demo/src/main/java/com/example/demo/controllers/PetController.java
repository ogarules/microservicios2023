package com.example.demo.controllers;

import javax.validation.constraints.Min;
import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.annotation.Validated;
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
import com.example.demo.validations.OnAdd;
import com.example.demo.validations.OnUpdate;
import com.querydsl.core.types.Predicate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/pets")
@Validated
public class PetController {
    
    @Autowired
    private IPetService service;

    @Autowired
    SmartValidator validator;

    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable Long id) {
        log.info("Controller requesting pet {}", id);
        return this.service.getPetById(id);
    }

    @GetMapping
    public Iterable<Pet> getAllPets(@QuerydslPredicate(root =Pet.class) Predicate predicate,
                                    @SortDefault(sort = "name", direction = Direction.ASC)
                                    @PageableDefault(size=5,page=0)
                                    Pageable page) {
        log.info("Controller requesting all pets");
        return this.service.getAllPets(predicate, page);
    }
    
    // @PostMapping
    // public ResponseEntity<Pet> addPet(@RequestBody Pet entity, BindingResult result) {
    //     log.info("Controller adding pet {}", entity);

    //     this.validator.validate(entity, result);
    //     if(result.hasErrors()){
    //         return new ResponseEntity<Pet>(entity, HttpStatus.BAD_REQUEST);
    //     }

    //     Pet pet = this.service.addPet(entity);
        
    //     return ResponseEntity.ok(pet);
    // }

    @PostMapping
    public ResponseEntity<Pet> addPet(@Validated({ OnAdd.class, Default.class}) @RequestBody Pet entity) {
        log.info("Controller adding pet {}", entity);

        Pet pet = this.service.addPet(entity);
        
        return ResponseEntity.ok(pet);
    }

    @PutMapping("/{id}")
    public Pet updatePet(@PathVariable Long id, @Validated({ OnUpdate.class, Default.class}) @RequestBody Pet entity) {
        log.info("Controller updating pet {}", id);
        
        return this.service.updatePet(entity, id);
    }
    
    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable @Min(1) Long id){
        this.service.deletePet(id);
    }
}
