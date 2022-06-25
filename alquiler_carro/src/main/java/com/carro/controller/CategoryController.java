package com.carro.controller;

import java.util.List;
import java.util.Optional;

import com.carro.model.Category;
import com.carro.pojo.CategoryPojo;
import com.carro.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
     * @param categoryPojo category id to get
     * @return the call of the getCategory method of the class categoryService
     */
    @GetMapping("/specificId")
    public Optional<Category> getCategory(@RequestBody CategoryPojo categoryPojo){
        Category category = new Category(categoryPojo);
        return categoryService.getCategory(category.getIdCategory());
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
