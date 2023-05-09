package com.example.mealplanner.meal.planner.service;

import com.example.mealplanner.meal.planner.MealPlannerApplication;
import com.example.mealplanner.meal.planner.dto.MealTimeDto;

import java.util.List;

public interface MealTimeService {

    MealTimeDto createMealTime(MealTimeDto mealTimeDto);

    List<MealTimeDto> getAllMealsTime();

    MealTimeDto getMealTimeById(long id);

    MealTimeDto updateMealTimeById(MealTimeDto mealTimeDto, long id);

    void deleteMealTimeById(long id);
}
