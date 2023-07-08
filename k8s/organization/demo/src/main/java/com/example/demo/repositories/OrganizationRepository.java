package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Organization;

public interface OrganizationRepository extends CrudRepository<Organization, String> {
    
}
