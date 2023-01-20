package com.vehicle.service;

import com.vehicle.dto.CategoryRequest;
import com.vehicle.dto.CategoryResponse;
import com.vehicle.dto.CategoryUpdate;
import com.vehicle.exception.CategoryAlreadyExistsException;
import com.vehicle.exception.CategoryNotFoundException;
import com.vehicle.factory.CategoryDataTest;
import com.vehicle.mapper.CategoryMapper;
import com.vehicle.model.Category;
import com.vehicle.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CategoryServiceTest {

    @InjectMocks
    CategoryService categoryService;
    @Mock
    CategoryRepository categoryRepository;
    @Mock
    CategoryMapper categoryMapper;
    private final Category category = new Category();

    @BeforeEach
    void setUp() {
        category.setIdCategory(1);
        category.setCategoryName("Category 1");
        category.setCategoryDescription("Category 1 description");
    }

    @Test
    void mustGetAllCategoriesOfDB() {
        CategoryResponse categoryResponse = CategoryDataTest.getCategoryResponse();

        when(categoryRepository.findAll()).thenReturn(List.of(category));
        when(categoryMapper.toCategoryResponseList(List.of(category))).thenReturn(List.of(categoryResponse));

        categoryService.getAllCategories();
        verify(categoryRepository).findAll();
    }

    @Test
    void mustGetSpecificCategoryByIdOfDB() {
        CategoryResponse categoryResponse = CategoryDataTest.getCategoryResponse();

        when(categoryRepository.findById(category.getIdCategory())).thenReturn(Optional.of(category));
        when(categoryMapper.toCategoryResponse(category)).thenReturn(categoryResponse);

        categoryService.getCategory(category.getIdCategory());
        verify(categoryRepository).findById(category.getIdCategory());
    }

    @Test
    void mustTrowCategoryNotFoundExceptionWhenTheCategoryCorrespondentToTheIdDoesNotExist() {
        when(categoryRepository.findById(5)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.getCategory(5));
    }

    @Test
    void mustSaveCategoryInTheDB() {
        CategoryRequest categoryRequest = CategoryDataTest.getCategoryRequest();

        when(categoryRepository.existsByCategoryName(categoryRequest.getCategoryName())).thenReturn(false);
        when(categoryMapper.toCategory(categoryRequest)).thenReturn(category);

        categoryService.saveCategory(categoryRequest);
        verify(categoryRepository).save(any(Category.class));
    }

    @Test
    void mustTrowCategoryAlreadyExistsExceptionWhenTheCategoryAlreadyExistsInTheDB() {
        CategoryRequest categoryRequest = CategoryDataTest.getCategoryRequest();

        when(categoryRepository.existsByCategoryName(categoryRequest.getCategoryName())).thenReturn(true);

        assertThrows(CategoryAlreadyExistsException.class, () -> categoryService.saveCategory(categoryRequest));
    }
    @Test
    void deleteCategory() {
        when(categoryRepository.existsById(category.getIdCategory())).thenReturn(true);

        categoryService.deleteCategory(category.getIdCategory());
        verify(categoryRepository).deleteById(category.getIdCategory());
    }

    @Test
    void mustTrowCategoryNotFoundExceptionWhenTheCategoryCorrespondentToTheIdDoesNotExistToDelete() {
        when(categoryRepository.existsById(5)).thenReturn(false);

        assertThrows(CategoryNotFoundException.class, () -> categoryService.deleteCategory(5));
    }
    @Test
    void updateCategory() {
        CategoryUpdate categoryUpdate = CategoryDataTest.getCategoryUpdate();

        when(categoryRepository.findById(categoryUpdate.getIdCategory())).thenReturn(Optional.of(category));

        categoryService.updateCategory(categoryUpdate);
        verify(categoryRepository).save(any(Category.class));
    }

    @Test
    void mustTrowCategoryNotFoundExceptionWhenTheCategoryCorrespondentToTheIdDoesNotExistToUpdate() {
        CategoryUpdate categoryUpdate = CategoryDataTest.getCategoryUpdate();

        when(categoryRepository.findById(categoryUpdate.getIdCategory())).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.updateCategory(categoryUpdate));
    }
}