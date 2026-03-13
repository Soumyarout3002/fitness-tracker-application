package com.project.Fitness_App.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.Fitness_App.Entity.ActivityType;
import com.project.Fitness_App.Entity.Recommendation;
import com.project.Fitness_App.Entity.user;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityResponse {

    private String id;
    private String userId;
    private ActivityType type;
    private Map<String,Object> additionalMetrics;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;



}
