package com.vehicle.pojo;

import com.vehicle.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor

public class CategoryPojo {
    private Integer idCategory;
    private String name;
    private String description;
    private List<Vehicle> vehicle;
}
