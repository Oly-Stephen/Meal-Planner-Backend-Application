package com.example.mealplanner.meal.planner.controller;

import com.example.mealplanner.meal.planner.dto.CategoryDto;
import com.example.mealplanner.meal.planner.dto.ProductDto;
import com.example.mealplanner.meal.planner.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Build Create Category REST API
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    // Build Get All Categories REST API
    @GetMapping
    public List<CategoryDto> getAllCategories(){
        return categoryService.getAllCategories();
    }

    // Build Get Category by ID REST API
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    // Update Category by id REST API
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable(name = "id") long id){
        CategoryDto categoryResponse = categoryService.updateCategoryById(categoryDto, id);

        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    // Delete Category by id REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable(name = "id") long id){
        categoryService.deleteCategoryById(id);

        return new ResponseEntity<>("Category deleted successfully.", HttpStatus.OK);
    }
}
