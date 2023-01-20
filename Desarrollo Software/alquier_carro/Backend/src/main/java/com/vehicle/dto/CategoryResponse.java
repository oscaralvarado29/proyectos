package com.vehicle.dto;

import com.vehicle.model.Vehicle;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CategoryResponse {
    private Integer idCategory;
    private String categoryName;
    private String categoryDescription;
    private List<Vehicle> vehicles;
}
