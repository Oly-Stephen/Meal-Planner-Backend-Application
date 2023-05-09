package com.example.mealplanner.meal.planner.service;

import com.example.mealplanner.meal.planner.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();

    ProductDto getProductById(long id);

    ProductDto updateProductById(ProductDto productDto, long id);

    void deleteProductById(long id);

    List<ProductDto> getProductsByCategory(Long categoryId);

    List<ProductDto> searchProducts(String query);
}
