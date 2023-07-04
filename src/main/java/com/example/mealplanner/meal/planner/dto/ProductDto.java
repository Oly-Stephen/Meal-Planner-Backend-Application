package com.example.mealplanner.meal.planner.dto;

import lombok.Data;

@Data
public class ProductDto {

    private Long id;
    private String name;
    private Long gramms;
    private Double protein;
    private Double fats;
    private Double carbs;
    private Double calories;
    private Long categoryId;
    private Long mealTimeId;

}
