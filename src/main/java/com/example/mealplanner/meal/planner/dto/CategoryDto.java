package com.example.mealplanner.meal.planner.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {

    private Long id;
    private String name;
    private String description;
    private List<ProductDto> products;
}
