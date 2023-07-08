package com.example.demo.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.models.Department;

@FeignClient(name="department")
public interface DepartmentService {
    
    @GetMapping("organization/{id}")
    List<Department> findByOrganizationId(@PathVariable String id);

    @GetMapping("organization/{id}/with-employees")
    List<Department> findByOrganizationIdWithEmployees(@PathVariable String id);
}
