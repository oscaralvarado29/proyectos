package com.carro.service;

import com.carro.model.Category;
import com.carro.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Oscar Alvarado
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * GET ALL
     * @return  the call of the getAll method of the class categoryRepository
     */
    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    /**
     * GET by specific id
     * @param categoryId category id to get
     * @return the call of the getCategory method of the class categoryRepository
     */
    public Optional<Category> getCategory (int categoryId) {
        return categoryRepository.getCategory(categoryId);
    }

    /**
     * POST
     * @param category object with category data
     * @return the call of the save method of the class categoryRepository if the category id don´t exist or is empty else return to category
     */
    public Category save(Category category) {
        if (category.getIdCategory() == null) {
            return categoryRepository.save(category);
        } else {
            Optional<Category> categoryData = categoryRepository.getCategory(category.getIdCategory());
            if (categoryData.isEmpty()) {
                return categoryRepository.save(category);
            } else {
                return category;
            }
        }
    }

    /**
     * UPDATE
     * @param category object with category data
     * @return the call of the save method of the class categoryRepository if the category exist else return to category
     */
    public Category update (Category category){
        if (category.getIdCategory() != null) {
            Optional<Category> categoryData = categoryRepository.getCategory(category.getIdCategory());
            if (categoryData.isPresent()) {
                if (category.getName() != null) {
                    categoryData.get().setName(category.getName());
                }
                if (category.getDescription() != null) {
                    categoryData.get().setDescription(category.getDescription());
                }
                return categoryRepository.save(categoryData.get());
            }
        }
        return category;
    }

    /**
     * DELETE
     * @param categoryId category id to delete
     * @return true if the category is deleted else return false
     */
    public boolean deleteCategory(Integer categoryId){
        return getCategory(categoryId).map(category -> {
            categoryRepository.delete(category);
            return true;
        }).orElse(false);
    }
}

