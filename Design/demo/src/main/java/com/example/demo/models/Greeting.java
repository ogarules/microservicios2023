package com.example.demo.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Greeting {
    private Long id;
    private String message;
    private String location;
}
