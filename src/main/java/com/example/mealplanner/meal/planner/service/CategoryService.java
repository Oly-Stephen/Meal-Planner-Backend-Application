package com.example.mealplanner.meal.planner.service;

import com.example.mealplanner.meal.planner.dto.CategoryDto;
import com.example.mealplanner.meal.planner.dto.ProductDto;
import com.example.mealplanner.meal.planner.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

//    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(long id);

    CategoryDto updateCategoryById(CategoryDto categoryDto, long id);

    void deleteCategoryById(long id);

//    List<CategoryDto> getAllMealUnderCategories();

//    List<ProductDto> importProductsFromXML(String xmlData);

    List<CategoryDto> getAllCategories();

//    ProductDto createNewProductByCategoryName(String categoryName, ProductDto productDto);


}
