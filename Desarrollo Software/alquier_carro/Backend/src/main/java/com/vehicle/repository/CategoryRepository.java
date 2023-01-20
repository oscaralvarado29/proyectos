package com.vehicle.repository;

import com.vehicle.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    boolean existsByCategoryName(String categoryName);
}
