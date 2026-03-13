package com.project.Fitness_App.Controller;

import com.project.Fitness_App.DTOs.ActivityRequest;
import com.project.Fitness_App.DTOs.ActivityResponse;
import com.project.Fitness_App.Sevices.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/activities")
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping("/tasks")
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest requsest) {
        return ResponseEntity.ok(activityService.trackActivity(requsest));
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getusersActivity(@RequestHeader (value = "X-User-ID") String userId) {
        return ResponseEntity.ok(activityService.getusersActivity(userId));
    }


    @DeleteMapping("/delete/{activityId}")
    public ResponseEntity<String> deleteActivity(@PathVariable String activityId) {

        activityService.deleteActivity(activityId);
        return ResponseEntity.ok("Activity deleted successfully");
    }
}
