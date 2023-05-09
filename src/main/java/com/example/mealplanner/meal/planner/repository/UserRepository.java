package com.example.mealplanner.meal.planner.repository;

import com.example.mealplanner.meal.planner.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
}
