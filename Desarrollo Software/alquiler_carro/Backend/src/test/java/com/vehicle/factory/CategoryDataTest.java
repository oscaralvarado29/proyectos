package com.vehicle.factory;

import com.vehicle.dto.CategoryRequest;
import com.vehicle.dto.CategoryResponse;
import com.vehicle.dto.CategoryUpdate;

public class CategoryDataTest {
    public static CategoryResponse getCategoryResponse() {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setIdCategory(1);
        categoryResponse.setCategoryName("Economy");
        categoryResponse.setCategoryDescription("Economy cars are the most affordable cars to rent.");
        return categoryResponse;
    }
    public static CategoryRequest getCategoryRequest() {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setCategoryName("Economy");
        categoryRequest.setCategoryDescription("Economy cars are the most affordable cars to rent.");
        return categoryRequest;
    }
    public static CategoryUpdate getCategoryUpdate() {
        CategoryUpdate categoryUpdate = new CategoryUpdate();
        categoryUpdate.setIdCategory(1);
        categoryUpdate.setName("Economy");
        categoryUpdate.setDescription("Economy cars are the most affordable cars to rent.");
        return categoryUpdate;
    }
}
