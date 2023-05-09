package com.example.mealplanner.meal.planner.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private Long weight;
    private Long Height;
    private Long age;
    private String activityStatus;
}
