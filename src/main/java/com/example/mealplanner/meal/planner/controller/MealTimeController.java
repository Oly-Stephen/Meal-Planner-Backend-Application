package com.example.mealplanner.meal.planner.controller;

import com.example.mealplanner.meal.planner.dto.MealTimeDto;
import com.example.mealplanner.meal.planner.service.MealTimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meal-times")
public class MealTimeController {

    private MealTimeService mealTimeService;

    public MealTimeController(MealTimeService mealTimeService) {
        this.mealTimeService = mealTimeService;
    }

    // Build Create MealTime REST API
    @PostMapping
    public ResponseEntity<MealTimeDto> createMealType(@RequestBody MealTimeDto mealTimeDto){
        return new ResponseEntity<>(mealTimeService.createMealTime(mealTimeDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<MealTimeDto> getAllMealsTime(){
        return mealTimeService.getAllMealsTime();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealTimeDto> getMealTimeById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(mealTimeService.getMealTimeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MealTimeDto> updateMealTimeById(@RequestBody MealTimeDto mealTimeDto,
                                                          @PathVariable(name = "id") long id){
        return ResponseEntity.ok(mealTimeService.updateMealTimeById(mealTimeDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMealTimeById(@PathVariable(name = "id") long id){

        mealTimeService.deleteMealTimeById(id);
        return ResponseEntity.ok("Meal Time Entity Deleted Successfully");
    }


}
