package com.reto5.intrface;

import com.reto5.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryInterface extends CrudRepository<Category,Integer> {
}
