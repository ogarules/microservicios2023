package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@Slf4j
public class EmployeeController {
    
    @Autowired
    EmployeeRepository repository;

    @GetMapping
    public Iterable<Employee> getAllEmployees() {
        log.info("K8s obteniendo todos los empleados");
        return repository.findAll();
    }
    
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable String id) {
        log.info("K8s obteniendo empleado por id {}", id);
        return repository.findById(id).orElseThrow();
    }
    
    @GetMapping("/department/{id}")
    public List<Employee> getEmployeesByDepartment(@PathVariable String id) {
        log.info("K8s get employees by department {}", id);
        return repository.findByDepartmentId(id);
    }

    @GetMapping("/organization/{id}")
    public List<Employee> getEmployeesByOrganization(@PathVariable String id) {
        log.info("K8s get employees by organization {}", id);
        return repository.findByOrganizationId(id);
    }
    
    @PostMapping
    public Employee addEmployee(@RequestBody Employee entity) {
        log.info("K8s addiong employee {}", entity);
        
        return repository.save(entity);
    }
    
}
    
