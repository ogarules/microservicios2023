package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Department;
import com.example.demo.repositories.DepartmentRepository;
import com.example.demo.services.EmployeeService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@Slf4j
public class DepartmentController {
    @Autowired
    DepartmentRepository repository;

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public Iterable<Department> getAllDepartments() {
        log.info("k8s getting all departments");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable String id) {
        log.info("k8s getting department by id {}", id);
        return repository.findById(id).orElseThrow();
    }
    
    @GetMapping(value="/organization/{id}")
    public Iterable<Department> getOrganizationDepartmnts(@PathVariable String id) {
        log.info("k8s getting organization {} deparments", id);
        return repository.findByOrganizationId(id);
    }
    
    @GetMapping(value="/organization/{id}/with-employees")
    public Iterable<Department> getOrganizationDepartmntsWithEmployees(@PathVariable String id) {
        log.info("k8s getting organization {} deparments with employees", id);
        List<Department> departments = repository.findByOrganizationId(id);

        departments.forEach(d -> d.setEmployees(this.employeeService.findByDepartmentId(d.getId())));

        return departments;
    }

    @PostMapping
    public Department addDepartment(@RequestBody Department entity) {
        log.info("k8s adding department");
        
        return repository.save(entity);
    }
    
}
