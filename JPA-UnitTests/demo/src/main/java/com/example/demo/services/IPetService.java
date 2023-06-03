package com.example.demo.services;

import org.springframework.data.domain.Pageable;

import com.example.demo.models.Pet;
import com.querydsl.core.types.Predicate;

public interface IPetService {
    
    Pet getPetById(Long id);
    Iterable<Pet> getAllPets(Predicate predicate, Pageable page);
    Pet addPet(Pet pet);
    Pet updatePet(Pet pet, Long id);
    Pet deletePet(Long id);

}
