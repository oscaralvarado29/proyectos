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
     * @return
     */
    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    /**
     * GET select a specific id
     * @param categoryId
     * @return
     */
    public Optional<Category> getCategory(int categoryId) {
        return categoryRepository.getCategory(categoryId);
    }

    /**
     * POST
     * @param category
     * @return
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
     * @param category
     * @return
     */
    public Category update(Category category){
        if(category.getId()!=null){
            Optional<Category>g= categoryRepository.getCategory(category.getId());
            if(!g.isEmpty()){
                if(category.getDescription()!=null){
                    g.get().setDescription(category.getDescription());
                }
                if(category.getName()!=null){
                    g.get().setName(category.getName());
                }
                return categoryRepository.save(g.get());
            }
        }
        return category;
    }

    /**
     * DELETE
     * @param categoryId
     * @return
     */
    public boolean deleteCategory(Integer categoryId){
        Boolean d=getCategory(categoryId).map(category -> {
            categoryRepository.delete(category);
            return true;
        }).orElse(false);
        return d;
    }

}
