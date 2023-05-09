package com.example.mealplanner.meal.planner.controller;

import com.example.mealplanner.meal.planner.dto.UserDto;
import com.example.mealplanner.meal.planner.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    private ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping
    private List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    private ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    private ResponseEntity<UserDto> updateUserById(@RequestBody UserDto userDto,
                                                   @PathVariable(name = "id") long id){
        return ResponseEntity.ok(userService.updateUserById(userDto, id));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteUserById(@PathVariable(name = "id") long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok("User Deleted Successfully");
    }
}
