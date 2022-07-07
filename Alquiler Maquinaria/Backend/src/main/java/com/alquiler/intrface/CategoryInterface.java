package com.alquiler.intrface;

import com.alquiler.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryInterface extends CrudRepository<Category,Integer> {
}
