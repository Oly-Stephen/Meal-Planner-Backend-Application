package com.example.mealplanner.meal.planner.entity;

import com.example.mealplanner.meal.planner.status.ActivityStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "names", nullable = false)
    private String name;

    @Column(name = "weights", nullable = false)
    private Long weight;

    @Column(name = "heights", nullable = false)
    private Long Height;

    @Column(name = "ages", nullable = false)
    private Long age;

    @Column(name = "daily_activities", nullable = false)
    @Enumerated(EnumType.STRING)
    private ActivityStatus activityStatus;
}
