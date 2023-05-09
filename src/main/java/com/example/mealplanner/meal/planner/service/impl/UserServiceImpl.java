package com.example.mealplanner.meal.planner.service.impl;

import com.example.mealplanner.meal.planner.dto.ProductDto;
import com.example.mealplanner.meal.planner.dto.UserDto;
import com.example.mealplanner.meal.planner.entity.Product;
import com.example.mealplanner.meal.planner.entity.User;
import com.example.mealplanner.meal.planner.exception.ResourceNotFoundException;
import com.example.mealplanner.meal.planner.repository.UserRepository;
import com.example.mealplanner.meal.planner.service.UserService;
import com.example.mealplanner.meal.planner.status.ActivityStatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        // Convert DTO to entity
        User user = mapToEntity(userDto);
        User newUser = userRepository.save(user);

        // Convert entity to DTO
        UserDto userDto1 = mapToDto(newUser);
        return userDto1;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user)-> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
        return mapToDto(user);
    }

    @Override
    public UserDto updateUserById(UserDto userDto, long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", id));

        user.setWeight(userDto.getWeight());
        user.setHeight(userDto.getHeight());
        user.setAge(userDto.getAge());
        user.setActivityStatus(ActivityStatus.valueOf(userDto.getActivityStatus()));


        User updatedUser = userRepository.save(user);
        return mapToDto(updatedUser);
    }

    @Override
    public void deleteUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);
    }


    // Convert entity to DTO
    private UserDto mapToDto(User user){
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    // Convert DTO to entity
    private User mapToEntity(UserDto userDto){
        User user = modelMapper.map(userDto, User.class);
        return user;
    }
}
