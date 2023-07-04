package com.example.mealplanner.meal.planner.controller;

import com.example.mealplanner.meal.planner.dto.ProductDto;
import com.example.mealplanner.meal.planner.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Build Create product REST API
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
    }

    // Build Get all products REST API
//    @GetMapping
//    public List<ProductDto> getAllProducts(){
//        return productService.getAllProducts();
//    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts(@RequestParam(defaultValue = "0") int page) {
        int pageSize = 9; // Set the desired page size

        Page<ProductDto> productPage = productService.getAllProducts(page, pageSize);
        List<ProductDto> products = productPage.getContent();

        return ResponseEntity.ok(products); // Add Metadata to this
    }




    // Build Get product by ID REST API
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // Update product by id REST API
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable(name = "id") long id){
        ProductDto productResponse = productService.updateProductById(productDto, id);

        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") long id){
        productService.deleteProductById(id);

        return new ResponseEntity<>("Product deleted successfully.", HttpStatus.OK);
    }

    // Build Get Products by Category ID REST API
    // http://localhost:8080/api/products/category/3
    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable(name = "id") Long categoryId){
        List<ProductDto> productDtos = productService.getProductsByCategory(categoryId);
        return ResponseEntity.ok(productDtos);
    }

    //Get All Products in Categories
    @GetMapping("/categories-products")
    public ResponseEntity<List<ProductDto>> getAllCategoriesProducts() {
        List<ProductDto> products = productService.getAllCategoriesProducts();
        return ResponseEntity.ok(products);
    }

    // Build Search Product by name REST API
    //http://localhost:8080/api/products/search?q=potatoe
    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> searchProducts(@RequestParam(name = "q") String query) {
        List<ProductDto> products = productService.searchProducts(query);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/categories-products-names")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryName() {
        List<ProductDto> products = productService.getProductsByCategoryName();
        return ResponseEntity.ok(products);
    }
}
