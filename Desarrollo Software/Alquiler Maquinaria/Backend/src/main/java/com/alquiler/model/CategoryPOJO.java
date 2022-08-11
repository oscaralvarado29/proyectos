package com.alquiler.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor

public class CategoryPOJO {
    private Integer id;
    private String name;
    private String description;
    private List<Machine> machines;
}
