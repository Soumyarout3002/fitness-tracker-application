package com.project.Fitness_App.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.Fitness_App.Entity.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ActivityRequest {


    private String userId;
    private ActivityType type;
    private Map<String,Object> additionalMetrics;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;


}
