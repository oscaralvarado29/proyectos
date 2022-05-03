package com.reto5.service;

import java.util.List;
import java.util.Optional;

import com.reto5.model.Category;
import com.reto5.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oscar Alvarado
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * GET
     * @return categoryRepository.getAll()
     */
    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    /**
     * GET select a specific id
     * @param categoryId id of the category
     * @return categoryRepository.getCategory(categoryId)
     */
    public Optional<Category> getCategory(int categoryId) {
        return categoryRepository.getCategory(categoryId);
    }

    /**
     * POST
     * @param category category to save
     * @return categoryRepository.save(category) if category.getId()==null or categoryRepository.getCategory(category.getId()).isEmpty()
     * else return category
     */
    public Category save(Category category) {
        if (category.getId()== null) {
            return categoryRepository.save(category);
        } else {
            Optional<Category> category1 = categoryRepository.getCategory(category.getId());
            if (category1.isEmpty()) {
                return categoryRepository.save(category);
            } else {
                return category;
            }
        }
    }

    /**
     * UPDATE
     * @param category category to update
     * @return categoryRepository.save(categoryUpdate.get()) if category.getId()!=null and categoryRepository.getCategory(category.getId()).isPresent()
     * else return category
     */
    public Category update(Category category){
        if(category.getId()!=null){
            Optional<Category>categoryUpdate= categoryRepository.getCategory(category.getId());
            if(categoryUpdate.isPresent()){
                if(category.getDescription()!=null){
                    categoryUpdate.get().setDescription(category.getDescription());
                }
                if(category.getName()!=null){
                    categoryUpdate.get().setName(category.getName());
                }
                return categoryRepository.save(categoryUpdate.get());
            }
        }
        return category;
    }

    /**
     * DELETE
     * @param categoryId id of the category to delete
     * @return true if the category is deleted else return false
     */
    public boolean deleteCategory(Integer categoryId){
        return getCategory(categoryId).map(category -> {
            categoryRepository.delete(category);
            return true;
        }).orElse(false);
    }

}
