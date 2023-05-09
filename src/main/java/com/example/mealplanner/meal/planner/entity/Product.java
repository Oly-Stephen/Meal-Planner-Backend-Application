package com.example.mealplanner.meal.planner.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "grams")
    private Long grams;

    @Column(nullable = false, name = "protein")
    private Long protein;

    @Column(nullable = false, name = "fats")
    private Long fats;

    @Column(nullable = false, name = "carbs")
    private Long carbs;

    @Column(nullable = false, name = "calories")
    private Long calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}