package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Employee;
import java.util.List;


@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {
    
    List<Employee> findByDepartmentId(String departmentId);
    List<Employee> findByOrganizationId(String organizationId);
    
}
