package com.example.mealplanner.meal.planner.repository;

import com.example.mealplanner.meal.planner.entity.Category;
import com.example.mealplanner.meal.planner.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryId(Long categoryId);

    @Query("SELECT p FROM Product p JOIN p.category c")
    List<Product> findAllCategoriesProducts();

    @Query("SELECT p FROM Product p WHERE " +
            "p.name LIKE CONCAT('%', :query, '%')")
    List<Product> searchProducts(String query);
}
