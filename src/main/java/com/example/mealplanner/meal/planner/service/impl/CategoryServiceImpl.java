package com.example.mealplanner.meal.planner.service.impl;

import com.example.mealplanner.meal.planner.dto.CategoryDto;
import com.example.mealplanner.meal.planner.dto.ProductDto;
import com.example.mealplanner.meal.planner.entity.Category;
import com.example.mealplanner.meal.planner.entity.Product;
import com.example.mealplanner.meal.planner.exception.ResourceNotFoundException;
import com.example.mealplanner.meal.planner.repository.CategoryRepository;
import com.example.mealplanner.meal.planner.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        // map DTO to Entity
        Category category = mapToEntity(categoryDto);
        Category newCategory = categoryRepository.save(category);

        // map Entity to DTO
        return mapToDTo(newCategory);
    }

    @Override
    public List<CategoryDto> getAllCategories() {

        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map((category) -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));
        return mapToDTo(category);
    }

    @Override
    public CategoryDto updateCategoryById(CategoryDto categoryDto, long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        Category updatedCategory = categoryRepository.save(category);
        return mapToDTo(updatedCategory);
    }

    @Override
    public void deleteCategoryById(long id) {

        Category category = categoryRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Category", "id", id));
        categoryRepository.delete(category);
    }


    // Convert Entity to DTO
    private CategoryDto mapToDTo(Category category){
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return categoryDto;
    }

    // Convert DTO to Entity
    private Category mapToEntity(CategoryDto categoryDto){
        Category category = modelMapper.map(categoryDto, Category.class);
        return category;
    }
}
