package com.project.Fitness_App.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RecommendationsRules {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    private ActivityType activityType;
    private Integer minDuration;
    private Integer maxDuration;
    private Integer minCalories;
    private Integer maxCalories;
    private String improvement;
    private String suggestion;
    private String safety;



}
