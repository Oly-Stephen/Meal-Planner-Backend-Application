package com.example.mealplanner.meal.planner.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "grams")
    private Long gramms;

    @Column(nullable = false, name = "protein")
    private Double protein;

    @Column(nullable = false, name = "fats")
    private Double fats;

    @Column(nullable = false, name = "carbs")
    private Double carbs;

    @Column(nullable = false, name = "calories")
    private Double calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mealTime_id")
    private MealTime mealTime;

    public Product(String name, Long gramms, Double protein, Double fats, Double carbs, Double calories) {
        this.name = name;
        this.gramms = gramms;
        this.protein = protein;
        this.fats = fats;
        this.carbs = carbs;
        this.calories = calories;
    }
}
