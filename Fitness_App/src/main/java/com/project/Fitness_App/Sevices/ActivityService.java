package com.project.Fitness_App.Sevices;

import com.project.Fitness_App.DTOs.ActivityRequest;
import com.project.Fitness_App.DTOs.ActivityResponse;
import com.project.Fitness_App.Entity.Activity;
import com.project.Fitness_App.Entity.user;
import com.project.Fitness_App.Repository.ActivityRepository;
import com.project.Fitness_App.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;


    public  ActivityResponse trackActivity(ActivityRequest requsest) {
        user users=userRepository.findById(requsest.getUserId()).orElseThrow(()-> new RuntimeException("Invalid user:" +requsest));
        Activity activity = Activity.builder()
                .user(users)
                .type(requsest.getType())
                .duration(requsest.getDuration())
                .caloriesBurned(requsest.getCaloriesBurned())
                .startTime(requsest.getStartTime())
                .additionalMetrics(requsest.getAdditionalMetrics())
                .build();
        Activity saveActivity =activityRepository.save(activity);
        return mapToResponse(saveActivity);
    }


    public void deleteActivity(String id) {

        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found"));

        activityRepository.delete(activity);
    }

    private ActivityResponse mapToResponse(Activity activity) {
        ActivityResponse response =new ActivityResponse();
        response.setId(activity.getId());
        response.setUserId(activity.getUser().getId());
        response.setType(activity.getType());
        response.setDuration(activity.getDuration());
        response.setCaloriesBurned(activity.getCaloriesBurned());
        response.setAdditionalMetrics(activity.getAdditionalMetrics());
        response.setCreatedAt(activity.getCreatedAt());
        response.setUpdateAt(activity.getUpdateAt());
        return response;
    }

    public List<ActivityResponse> getusersActivity(String userId) {
        List<Activity> activityList=activityRepository.findByUserId(userId);
        return activityList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
