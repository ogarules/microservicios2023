package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Organization;
import com.example.demo.repositories.OrganizationRepository;
import com.example.demo.services.DepartmentService;
import com.example.demo.services.EmployeeService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@Slf4j
public class OrganizationController {
    
    @Autowired
    OrganizationRepository repository;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public Iterable<Organization> getAllOrganizations() {
        log.info("k8s getting all organizations");
        return repository.findAll();
    }
    
    @GetMapping("/{id}")
    public Organization getOrganizationById(@PathVariable String id) {
        log.info("kbs getting organization {}", id);
        return repository.findById(id).orElseThrow();
    }
    
    @GetMapping("/{id}/departments")
    public Organization getOrganizationWithDepartments(@PathVariable String id) {
        log.info("k8s getting organization {} with departments", id);

        Organization organization = repository.findById(id).orElseThrow();

        organization.setDepartments(departmentService.findByOrganizationId(id));

        return organization;
    }

    @GetMapping("/{id}/departments/employees")
    public Organization getOrganizationWithDepartmentsWithEmployees(@PathVariable String id) {
        log.info("k8s getting organization {} with departments with employees", id);

        Organization organization = repository.findById(id).orElseThrow();

        organization.setDepartments(departmentService.findByOrganizationIdWithEmployees(id));

        return organization;
    }

    @GetMapping("/{id}/employees")
    public Organization getOrganizationWithEmployees(@PathVariable String id) {
        log.info("k8s getting organization {} with employees", id);

        Organization organization = repository.findById(id).orElseThrow();

        organization.setEmployees(employeeService.findByOrganizationId(id));

        return organization;
    }

    @PostMapping
    public Organization addOrganization(@RequestBody Organization entity) {
        log.info("k8s adding organization {}", entity);
        
        return repository.save(entity);
    }
    
    
}
