package com.example.mealplanner.meal.planner.service;

import com.example.mealplanner.meal.planner.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto getUserById(long id);

    UserDto updateUserById(UserDto userDto, long id);

    void deleteUserById(long id);
}
