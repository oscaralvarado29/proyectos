package com.vehicle.intrface;

import com.vehicle.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryInterface extends CrudRepository<Category,Integer> {
}
