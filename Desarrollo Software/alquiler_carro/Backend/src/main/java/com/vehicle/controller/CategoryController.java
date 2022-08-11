package com.vehicle.controller;

import java.util.List;
import java.util.Optional;

import com.vehicle.model.Category;
import com.vehicle.pojo.CategoryPojo;
import com.vehicle.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Oscar Alvarado
 */
@RestController
@RequestMapping("/Category")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})

public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * GET all
     * @return the call of the getAll method of the class categoryService
     */
    @GetMapping("/all")
    public List<Category> getCategory(){
        return categoryService.getAll();
    }

    /**
     * GET by specific id
     * @param categoryId category id to get
     * @return the call of the getCategory method of the class categoryService
     */
    @GetMapping("/{id}")
    public Optional<Category> getCategory(@PathVariable("id") int categoryId) {
        return categoryService.getCategory(categoryId);
    }

    /**
     * POST
     * @param categoryPojo pojo created with category data
     * @return the call of the save method of the class categoryService
     */
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Category save(@RequestBody CategoryPojo categoryPojo) {
        Category category = new Category(categoryPojo);
        return categoryService.save(category);
    }

    /**
     * PuT
     * @param categoryPojo pojo created with category data
     * @return the call of the update method of the class categoryService
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Category update(@RequestBody CategoryPojo categoryPojo) {
        Category category = new Category(categoryPojo);
        return categoryService.update(category);
    }

    /**
     * DELETE
     * @param categoryPojo category id to delete
     * @return the call of the deleteCategory method of the class categoryService
     */
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@RequestBody CategoryPojo categoryPojo) {
        Category category = new Category(categoryPojo);
        return categoryService.deleteCategory(category.getIdCategory());
    }
}
