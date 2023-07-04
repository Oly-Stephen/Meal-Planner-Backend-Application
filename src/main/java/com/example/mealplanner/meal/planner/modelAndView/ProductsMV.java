package com.example.mealplanner.meal.planner.modelAndView;

import com.example.mealplanner.meal.planner.dto.CategoryDto;
import com.example.mealplanner.meal.planner.dto.MealTimeDto;
import com.example.mealplanner.meal.planner.dto.ProductDto;
import com.example.mealplanner.meal.planner.entity.MealTime;
import com.example.mealplanner.meal.planner.repository.MealTimeRepository;
import com.example.mealplanner.meal.planner.service.CategoryService;
import com.example.mealplanner.meal.planner.service.MealTimeService;
import com.example.mealplanner.meal.planner.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsMV {

    private final ProductService productService;
    private final CategoryService categoryService;

    private final MealTimeService mealTimeService;
    private final MealTimeRepository mealTimeRepository;

    public ProductsMV(ProductService productService,
                      CategoryService categoryService,
                      MealTimeService mealTimeService,
                      MealTimeRepository mealTimeRepository) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.mealTimeService = mealTimeService;
        this.mealTimeRepository = mealTimeRepository;
    }

    @GetMapping
    public String responseList(Model model) {
        // Get products from the "categories" method and add them to the model
        model.addAttribute("categories", categoryService.getAllCategories());

        int page = 0;
        int pageSize = 9; // Set the desired page size

        Page<ProductDto> productPage = productService.getAllProducts(page, pageSize);

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());

        return "index";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "edit-product";
    }

    @PostMapping("/{id}")
    public String editProduct(@PathVariable Long id, @ModelAttribute("product") ProductDto productDto,
                              Model model) {

        // get product from database by id
        ProductDto existingProduct = productService.getProductById(id);

        // copy properties from DTO to entity object
        BeanUtils.copyProperties(productDto, existingProduct, "id");

        // save updated product object
        productService.updateProductById(existingProduct, id);
        return "redirect:/products";
    }

    // Handler method to handle delete product
    @GetMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }

    @GetMapping("/new")
    public String createProductResponse(Model model) {
        List<CategoryDto> categories = categoryService.getAllCategories();
        ProductDto productDto = new ProductDto();
        model.addAttribute("product", productDto);
        model.addAttribute("categories", categories);
        return "create-products.html";
    }


    @PostMapping
    public String addProductResponse(@ModelAttribute("product") ProductDto productDto) {

        productService.createProduct(productDto);
        return "redirect:/products";
    }


    //Meal plan Section

    @GetMapping("/meal-plan")
    public String MealResponseList(Model model) {

        // Get Meal from the "categories" method and add them to the model
        model.addAttribute("mealTimes", mealTimeService.getAllMealsTime());

        return "meal-plan-section";
    }

    @PostMapping("/meal-plan")
    public String createMealPlan(@ModelAttribute("mealTime") MealTimeDto mealTimeDto) {
        // Save the mealTimeDto to the service
        mealTimeService.createMealTime(mealTimeDto);

        // Redirect to the desired page after successful creation
        return "redirect:/products/meal-plan";
    }
}
