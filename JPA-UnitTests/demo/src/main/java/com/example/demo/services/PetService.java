package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.models.Pet;
import com.example.demo.repositories.PetRepository;
import com.querydsl.core.types.Predicate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PetService implements IPetService {
    
    @Autowired
    PetRepository repository;

    @Override
    public Pet getPetById(Long id) {
        log.info("Obteniendo mascota {}", id);
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public Iterable<Pet> getAllPets(Predicate predicate, Pageable page) {
        log.info("Obteneiendo todas las mascotas....");

        return this.repository.findAll(predicate, page);
    }

    @Override
    public Pet addPet(Pet pet) {
        log.info("Agregando mascota {}", pet);

        return this.repository.save(pet);
    }

    @Override
    public Pet updatePet(Pet pet, Long id) {
        log.info("Actualizando mascota {}", pet);

        Pet petDb = this.repository.findById(id).orElseThrow();
        petDb.setAge(pet.getAge());
        petDb.setName(pet.getName());
        petDb.setSpecies(pet.getSpecies());
        petDb.setTag(pet.getTag());

        return this.repository.save(petDb);
    }

    @Override
    public Pet deletePet(Long id) {

        Pet petDb = this.repository.findById(id).orElseThrow();
        this.repository.deleteById(id);

        log.info("Eliminando mascota {}", petDb);

        return petDb;
    }

    
}
