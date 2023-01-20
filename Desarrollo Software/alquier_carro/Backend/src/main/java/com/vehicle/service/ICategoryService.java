package com.vehicle.service;

import com.vehicle.dto.CategoryRequest;
import com.vehicle.dto.CategoryResponse;
import com.vehicle.dto.CategoryUpdate;
import java.util.List;

public interface ICategoryService {
    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategory (int categoryId);
    void saveCategory(CategoryRequest categoryRequest);
    void deleteCategory(int categoryId);
    void updateCategory(CategoryUpdate categoryUpdate);
    }
