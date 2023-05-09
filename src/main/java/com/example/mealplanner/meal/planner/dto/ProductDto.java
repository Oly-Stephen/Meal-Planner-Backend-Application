package com.example.mealplanner.meal.planner.dto;

import lombok.Data;

@Data
public class ProductDto {

    private Long id;

    private String name;

    private Long grams;

    private Long protein;

    private Long fats;

    private Long carbs;

    private Long calories;

    private Long categoryId;
}
