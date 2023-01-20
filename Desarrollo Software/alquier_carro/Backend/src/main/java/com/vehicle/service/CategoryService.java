package com.vehicle.service;

import com.vehicle.dto.CategoryRequest;
import com.vehicle.dto.CategoryResponse;
import com.vehicle.dto.CategoryUpdate;
import com.vehicle.exception.CategoryNotFoundException;
import com.vehicle.exception.CategoryAlreadyExistsException;
import com.vehicle.mapper.CategoryMapper;
import com.vehicle.model.Category;
import com.vehicle.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 *
 * @author Oscar Alvarado
 */
@RequiredArgsConstructor
@Service
@Transactional
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    /**
     * @return all categories from database
     */
    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryMapper.toCategoryResponseList(categoryRepository.findAll());
    }

    /**
     * @param categoryId id of category to search
     * @return category with id equals to categoryId
     */
    @Override
    public CategoryResponse getCategory(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
        return categoryMapper.toCategoryResponse(category);
    }

    /**
     * @param categoryRequest category to save
     */

    @Override
    public void saveCategory(CategoryRequest categoryRequest) {
        if (categoryRepository.existsByCategoryName(categoryRequest.getCategoryName())) {
            throw new CategoryAlreadyExistsException();
        }
        categoryRepository.save(categoryMapper.toCategory(categoryRequest));
    }

    /**
     * @param categoryId id of category to delete
     */
    @Override
    public void deleteCategory(int categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new CategoryNotFoundException();
        }
        categoryRepository.deleteById(categoryId);
    }

    /**
     * @param categoryUpdate category to update
     */
    @Override
    public void updateCategory(CategoryUpdate categoryUpdate) {
        Category categoryInDB = categoryRepository.findById(categoryUpdate.getIdCategory()).orElseThrow(CategoryNotFoundException::new);
        if (categoryUpdate.getName() != null) {
            categoryInDB.setCategoryName(categoryUpdate.getName());
        }
        if (categoryUpdate.getDescription() != null) {
            categoryInDB.setCategoryDescription(categoryUpdate.getDescription());
        }
        categoryRepository.save(categoryInDB);
    }
}

