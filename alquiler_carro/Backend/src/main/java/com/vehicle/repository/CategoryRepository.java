package com.vehicle.repository;

import com.vehicle.intrface.CategoryInterface;
import com.vehicle.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Oscar Alvarado
 */
@Repository
public class CategoryRepository {
    @Autowired
    private CategoryInterface categoryInterface;

    /**
     * get all
     * @return the call of the findAll method of the interface CategoryInterface
     */
    public List<Category> getAll(){
        return (List<Category>) categoryInterface.findAll();
    }

    /**
     * get by specific id
     * @param categoryId category id to get
     * @return the call of the findById method of the interface CategoryInterface
     */
    public Optional<Category> getCategory(int categoryId){
        return categoryInterface.findById(categoryId);
    }

    /**
     * Insert
     * @param category objet with category data
     * @return the call of the save method of the interface CategoryInterface
     */
    public Category save(Category category){
        return categoryInterface.save(category);
    }

    /**
     * Delete
     * @param category objet with category data to delete
     */
    public void delete(Category category){
        categoryInterface.delete(category);
    }

}
