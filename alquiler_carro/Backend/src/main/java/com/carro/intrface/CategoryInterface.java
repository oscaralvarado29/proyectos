package com.carro.intrface;

import com.carro.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryInterface extends CrudRepository<Category,Integer> {
}
