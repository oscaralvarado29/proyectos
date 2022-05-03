package com.reto5.repository;

import java.util.List;
import java.util.Optional;
import com.reto5.intrface.CategoryInterface;
import com.reto5.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Oscar Alvarado
 */
@Repository
public class CategoryRepository {
    @Autowired
    private CategoryInterface categoryInterface;

    /**
     * Select
     * @return categoryInterface.findAll()
     */
    public List<Category> getAll(){
        return (List<Category>) categoryInterface.findAll();
    }

    /**
     * Select by specific id
     * @param id id of the category
     * @return categoryInterface.findById(id)
     */
    public Optional<Category> getCategory(int id){
        return categoryInterface.findById(id);
    }

    /**
     * Save
     * @param category category to save
     * @return categoryInterface.save(category)
     */
    public Category save(Category category){
        return categoryInterface.save(category);
    }

    /**
     * Delete
     * @param category category to delete
     */
    public void delete(Category category){
        categoryInterface.delete(category);
    }
}
