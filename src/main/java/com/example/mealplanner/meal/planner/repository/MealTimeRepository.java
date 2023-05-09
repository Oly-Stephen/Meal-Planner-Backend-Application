package com.example.mealplanner.meal.planner.repository;

import com.example.mealplanner.meal.planner.entity.MealTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealTimeRepository extends JpaRepository<MealTime, Long> {
}
