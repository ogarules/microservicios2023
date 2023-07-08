package com.example.demo.models;

import lombok.Data;

@Data
public class Employee {
    private String id;
    private String organizationId;
    private String departmentId;

    private String name;
    private int age;
    private String position;

}
