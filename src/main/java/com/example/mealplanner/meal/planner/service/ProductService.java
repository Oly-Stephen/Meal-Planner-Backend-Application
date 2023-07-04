package com.example.mealplanner.meal.planner.service;

import com.example.mealplanner.meal.planner.dto.CategoryDto;
import com.example.mealplanner.meal.planner.dto.ProductDto;
import com.example.mealplanner.meal.planner.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto);

//    List<ProductDto> getAllProducts();

    Page<ProductDto> getAllProducts(int page, int pageSize);

    ProductDto getProductById(long id);

    ProductDto updateProductById(ProductDto productDto, long id);

    void deleteProductById(long id);

    List<ProductDto> getProductsByCategory(Long categoryId);

    List<ProductDto> searchProducts(String query);

    List<ProductDto> getAllCategoriesProducts();

    List<ProductDto> getProductsByCategoryName();
}
