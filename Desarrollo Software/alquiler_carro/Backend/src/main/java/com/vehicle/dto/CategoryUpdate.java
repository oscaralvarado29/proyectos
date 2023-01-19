package com.vehicle.dto;

import com.vehicle.model.Vehicle;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryUpdate {
    private Integer idCategory;
    private String name;
    private String description;
}
