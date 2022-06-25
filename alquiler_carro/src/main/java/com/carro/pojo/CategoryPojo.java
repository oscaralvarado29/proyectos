package com.carro.pojo;
import com.carro.model.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CategoryPojo {
    private Integer idCategory;
    private String name;
    private String description;
    private List<Car> car;
}
