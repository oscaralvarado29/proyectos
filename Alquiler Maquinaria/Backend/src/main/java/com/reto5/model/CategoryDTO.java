package com.reto5.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor

public class CategoryDTO {
    private Integer id;
    private String name;
    private String description;
    private List<Machine> machines;
}
