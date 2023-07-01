package com.example.demo.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Note;
import com.example.demo.repositories.NoteRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("notes")
@RequiredArgsConstructor
public class NoteController {
    
    private final NoteRepository repository;

    @GetMapping
    public List<Note> getAllNotes() {
        return repository.findAll();
    }
    
    @GetMapping(value="/{id}")
    public Note getNote(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow();
    }
    
    @PostMapping()
    public Note addNote(@RequestBody Note entity) {
        
        return repository.save(entity);
    }
    
    @PutMapping(value="/{id}")
    public Note addNote(@PathVariable Integer id, @RequestBody Note entity, Principal user) {
        entity.setUserId(user.getName());
        return repository.save(entity);
    }

    @GetMapping("/user")
    public List<Note> getUserNotes(Principal user){
        return repository.findByUserId(user.getName());
    } 

    @DeleteMapping(value="/{id}")
    public void delete(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
