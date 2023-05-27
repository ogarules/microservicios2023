package com.example.demo.services;

import com.example.demo.models.Pet;

public interface IPetService {
    
    Pet getPetById(Long id);
    Iterable<Pet> getAllPets();
    Pet addPet(Pet pet);
    Pet updatePet(Pet pet, Long id);
    Pet deletePet(Long id);

}
