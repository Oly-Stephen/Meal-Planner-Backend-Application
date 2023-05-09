package com.example.mealplanner.meal.planner.service.impl;

import com.example.mealplanner.meal.planner.dto.ProductDto;
import com.example.mealplanner.meal.planner.entity.Category;
import com.example.mealplanner.meal.planner.entity.Product;
import com.example.mealplanner.meal.planner.exception.ResourceNotFoundException;
import com.example.mealplanner.meal.planner.repository.CategoryRepository;
import com.example.mealplanner.meal.planner.repository.ProductRepository;
import com.example.mealplanner.meal.planner.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("Category", "id", productDto.getCategoryId()));

        // Convert DTO to entity
        Product product = mapToEntity(productDto);
        product.setCategory(category);
        Product newProduct = productRepository.save(product);

        // Convert entity to DTO
        ProductDto productDto1 = mapToDto(newProduct);
        return productDto1;
    }

    @Override
    public List<ProductDto> getAllProducts() {

        List<Product> products = productRepository.findAll();
        return products.stream().map((product) -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product", "id", id));
        return mapToDto(product);
    }

    @Override
    public ProductDto updateProductById(ProductDto productDto, long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", productDto.getCategoryId()));

        product.setName(productDto.getName());
        product.setGrams(productDto.getGrams());
        product.setProtein(productDto.getProtein());
        product.setFats(productDto.getFats());
        product.setCarbs(productDto.getCarbs());
        product.setCalories(productDto.getCalories());
        product.setCategory(category);

        Product updatedProduct = productRepository.save(product);
        return mapToDto(updatedProduct);
    }

    @Override
    public void deleteProductById(long id) {

        Product product = productRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Product", "id", id));
        productRepository.delete(product);
    }

    @Override
    public List<ProductDto> getProductsByCategory(Long categoryId) {

        categoryRepository.findById(categoryId)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Category", "id", categoryId));

        List<Product> products = productRepository.findByCategoryId(categoryId);

        return products.stream().map((product) -> mapToDto(product))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> searchProducts(String query) {

        List<Product> products = productRepository.searchProducts(query);

        return products.stream().map((product) -> mapToDto(product))
                .collect(Collectors.toList());
    }


    // Convert entity to DTO
    private ProductDto mapToDto(Product product){
        ProductDto productDto = modelMapper.map(product, ProductDto.class);

        return productDto;
    }

    // Convert DTO to entity
    private Product mapToEntity(ProductDto productDto){
        Product product = modelMapper.map(productDto, Product.class);

        return product;
    }
}
