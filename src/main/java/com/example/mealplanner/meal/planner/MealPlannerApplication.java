package com.example.mealplanner.meal.planner;

import com.example.mealplanner.meal.planner.entity.Product;
import com.example.mealplanner.meal.planner.repository.CategoryRepository;
import com.example.mealplanner.meal.planner.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MealPlannerApplication{

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args)  {
		SpringApplication.run(MealPlannerApplication.class, args);
	}

}
