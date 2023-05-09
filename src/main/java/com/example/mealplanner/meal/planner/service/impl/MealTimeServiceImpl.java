package com.example.mealplanner.meal.planner.service.impl;

import com.example.mealplanner.meal.planner.dto.MealTimeDto;
import com.example.mealplanner.meal.planner.entity.MealTime;
import com.example.mealplanner.meal.planner.exception.ResourceNotFoundException;
import com.example.mealplanner.meal.planner.repository.MealTimeRepository;
import com.example.mealplanner.meal.planner.service.MealTimeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealTimeServiceImpl implements MealTimeService {

    private MealTimeRepository mealTimeRepository;
    private ModelMapper modelMapper;

    public MealTimeServiceImpl(MealTimeRepository mealTimeRepository,
                               ModelMapper modelMapper) {
        this.mealTimeRepository = mealTimeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MealTimeDto createMealTime(MealTimeDto mealTimeDto) {

        // Convert DTO to Entity
        MealTime mealTime = mapToEntity(mealTimeDto);
        MealTime newMealTime = mealTimeRepository.save(mealTime);

        // Convert Entity to DTO
        return mapToDto(newMealTime);
    }

    @Override
    public List<MealTimeDto> getAllMealsTime() {
        List<MealTime> mealTimes = mealTimeRepository.findAll();
        return mealTimes.stream().map((mealTime) -> modelMapper.map(mealTime, MealTimeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public MealTimeDto getMealTimeById(long id) {
        MealTime mealTime = mealTimeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Meal Time", "id", id));
        return mapToDto(mealTime);
    }

    @Override
    public MealTimeDto updateMealTimeById(MealTimeDto mealTimeDto, long id) {

        MealTime mealTime = mealTimeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Meal Time", "id", id));

        mealTime.setName(mealTimeDto.getName());

        MealTime updatedMealTime = mealTimeRepository.save(mealTime);
        return mapToDto(updatedMealTime);
    }

    @Override
    public void deleteMealTimeById(long id) {

        MealTime mealTime = mealTimeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Meal Time", "id", id));
        mealTimeRepository.delete(mealTime);
    }


    // Convert Entity to DTO
    private MealTimeDto mapToDto(MealTime mealTime){
        MealTimeDto mealTimeDto = modelMapper.map(mealTime, MealTimeDto.class);
        return mealTimeDto;
    }

    // Convert DTO to Entity
    private MealTime mapToEntity(MealTimeDto mealTimeDto){
        MealTime mealTime = modelMapper.map(mealTimeDto, MealTime.class);
        return mealTime;
    }
}
