package com.example.mealplanner.meal.planner.repository;

import com.example.mealplanner.meal.planner.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
