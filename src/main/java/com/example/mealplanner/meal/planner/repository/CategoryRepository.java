package com.example.mealplanner.meal.planner.repository;

import com.example.mealplanner.meal.planner.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);
}
