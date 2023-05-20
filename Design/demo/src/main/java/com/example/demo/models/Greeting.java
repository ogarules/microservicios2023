package com.example.demo.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(description = "Modelo de greeting")
public class Greeting {

    @ApiModelProperty(notes = "Id intenerno del greeting", example = "123")
    private Long id;

    @ApiModelProperty(notes="MEnsaje del greeting", example = "Hello world!!!")
    private String message;

    @ApiModelProperty(notes = "Origen del saludo", example = "CDMX")
    private String location;
}
