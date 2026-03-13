package com.project.Fitness_App.Controller;

import com.project.Fitness_App.DTOs.RecommendationRequest;
import com.project.Fitness_App.DTOs.RecommendationResponse;
import com.project.Fitness_App.Entity.Recommendation;
import com.project.Fitness_App.Sevices.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/recommendation")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @PostMapping("/generate")
    public ResponseEntity<RecommendationResponse> generateRecommendation(@RequestBody RecommendationRequest request
                                                                 ){
        RecommendationResponse recommendation=recommendationService.generateRecommendation(request);
                return ResponseEntity.ok(recommendation);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>>getuserRecommendation(@PathVariable String userId){

         List<Recommendation> recoList=recommendationService.getuserRecommendation(userId);
         return ResponseEntity.ok(recoList);

    }

    @GetMapping("/activity/{activityid}")
    public ResponseEntity<List<Recommendation>>getactivityRecommendation(@PathVariable String activityid){

        List<Recommendation> recoList=recommendationService.getActivityRecommendation(activityid);
        return ResponseEntity.ok(recoList);

    }

}
