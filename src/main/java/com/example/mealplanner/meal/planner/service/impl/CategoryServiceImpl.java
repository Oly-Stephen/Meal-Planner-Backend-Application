package com.example.mealplanner.meal.planner.service.impl;

import com.example.mealplanner.meal.planner.dto.CategoryDto;
import com.example.mealplanner.meal.planner.dto.ProductDto;
import com.example.mealplanner.meal.planner.entity.Category;
import com.example.mealplanner.meal.planner.entity.Product;
import com.example.mealplanner.meal.planner.exception.ResourceNotFoundException;
import com.example.mealplanner.meal.planner.repository.CategoryRepository;
import com.example.mealplanner.meal.planner.repository.ProductRepository;
import com.example.mealplanner.meal.planner.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private ProductRepository productRepository;

    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ModelMapper modelMapper,
                               ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
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


//    @Override
//    public ProductDto createNewProductByCategoryName(String categoryName, ProductDto productDto) {
//        // Retrieve the category by name
//        Category category = categoryRepository.findByName(categoryName)
//                .orElseThrow(() -> new ResourceNotFoundException("Category", "name", categoryName));
//
//        // Create a new product entity
//        Product product = modelMapper.map(productDto, Product.class);
//
//        // Set the category for the product
//        product.setCategory(category);
//
//        // Save the product using the product repository
//        Product newProduct = productRepository.save(product);
//
//        // Map the created product entity to a DTO and return it
//        return modelMapper.map(newProduct, ProductDto.class);
//    }


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


    public List<CategoryDto> getAllMealUnderCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();

        for (Category category : categories) {
            List<Product> products = productRepository.findByCategoryId(category.getId());
            List<ProductDto> productDtos = new ArrayList<>();

            for (Product product : products) {
                ProductDto productDto = new ProductDto();
                productDto.setId(product.getId());
                productDto.setName(product.getName());
                productDto.setGramms(product.getGramms());
                productDto.setProtein(product.getProtein());
                productDto.setFats(product.getFats());
                productDto.setCarbs(product.getCarbs());
                productDto.setCalories(product.getCalories());
                productDtos.add(productDto);
            }

            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            categoryDto.setDescription(category.getDescription());
            categoryDto.setProducts(productDtos);

            categoryDtos.add(categoryDto);
        }

        return categoryDtos;
    }

}
