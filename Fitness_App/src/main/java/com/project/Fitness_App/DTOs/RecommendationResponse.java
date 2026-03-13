package com.project.Fitness_App.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationResponse {
    private String userId;
    private String activityId;
    private String type;
    private List<String> improvements;
    private List<String> suggestions;
    private List<String> safety;
}
