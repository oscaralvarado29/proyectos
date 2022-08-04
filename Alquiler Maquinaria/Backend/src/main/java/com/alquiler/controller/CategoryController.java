package com.alquiler.controller;

import java.util.List;
import java.util.Optional;

import com.alquiler.model.Category;
import com.alquiler.service.CategoryService;
import com.alquiler.model.CategoryPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     * GET
     * @return categoryService.getAll()
     */
    @GetMapping("/all")
    public List<Category> getCategory(){
        return categoryService.getAll();
    }

    /**
     * GET for specific id
     * @param categoryId id of the category
     * @return categoryService.getCategory(categoryId)
     */
    @GetMapping("/{id}")
    public Optional<Category> getCategory(@PathVariable("id") int categoryId) {
        return categoryService.getCategory(categoryId);
    }

    /**
     * POST
     * @param categoryDTO POJO of the category
     * @return categoryService.save(category)
     */
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Category save(@RequestBody CategoryPOJO categoryDTO) {
        Category category = new Category(categoryDTO);
        return categoryService.save(category);
    }

    /**
     * PUT
     * @param categoryDTO POJO of the category
     * @return categoryService.update(category)
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Category update(@RequestBody CategoryPOJO categoryDTO) {
        Category category = new Category(categoryDTO);
        return categoryService.update(category);
    }

    /**
     * DELETE
     * @param categoryId id of the category
     * @return categoryService.deleteCategory(categoryId
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") Integer categoryId) {
        return categoryService.deleteCategory(categoryId);
    }
}
