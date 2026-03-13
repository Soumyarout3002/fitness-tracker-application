package com.project.Fitness_App.Sevices;

import com.project.Fitness_App.DTOs.RecommendationRequest;
import com.project.Fitness_App.DTOs.RecommendationResponse;
import com.project.Fitness_App.Entity.Activity;
import com.project.Fitness_App.Entity.Recommendation;
import com.project.Fitness_App.Entity.RecommendationsRules;
import com.project.Fitness_App.Entity.user;
import com.project.Fitness_App.Repository.ActivityRepository;
import com.project.Fitness_App.Repository.RecommationRepository;
import com.project.Fitness_App.Repository.RecommendationruleRepository;
import com.project.Fitness_App.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private final RecommationRepository recommationRepository;
    private final RecommendationruleRepository recommendationruleRepository;

    public RecommendationResponse generateRecommendation(RecommendationRequest request) {
        user users=userRepository.findById(request.getUserId())
                .orElseThrow(()->new RuntimeException("User not Found" +request));

        Activity activity =activityRepository.findById(request.getActivityId())
                .orElseThrow(()->new RuntimeException("Activity not Found" +request));

        List<RecommendationsRules> rules = recommendationruleRepository.findRuleByActivityType(activity.getType());

        List<String> improvements = new ArrayList<>();
        List<String> suggestions = new ArrayList<>();
        List<String> safety = new ArrayList<>();

        for (RecommendationsRules rule : rules) {
            if (!match(rule.getMinDuration(), rule.getMaxDuration(), activity.getDuration())) continue;
            if (!match(rule.getMinCalories(), rule.getMaxCalories(), activity.getCaloriesBurned())) continue;

            if (rule.getImprovement() != null) improvements.add(rule.getImprovement());
            if (rule.getSuggestion() != null) suggestions.add(rule.getSuggestion());
            if (rule.getSafety() != null) safety.add(rule.getSafety());
        }
        
        Recommendation recommendation= Recommendation.builder()
                .user(users)
                .activity(activity)
                .type(activity.getType())
                .improvements(improvements)
                .suggestions(suggestions)
                .safety(safety)
                .build();
        Recommendation savedrec=recommationRepository.save(recommendation);

        return mapToresponse(savedrec);
    }

    private RecommendationResponse mapToresponse(Recommendation savedrec) {
        RecommendationResponse response =new RecommendationResponse();
        response.setUserId(savedrec.getUser().getId());
        response.setActivityId(savedrec.getActivity().getId());
        response.setType(savedrec.getType().name());
        response.setImprovements(savedrec.getImprovements());
        response.setSuggestions(savedrec.getSuggestions());
        response.setSafety(savedrec.getSafety());

        return response;

    }

    private boolean match(Integer min, Integer max, Integer value) {
        if (min != null && value < min) return false;
        if (max != null && value > max) return false;
        return true;
    }


    public List<Recommendation> getuserRecommendation(String userId) {
        return recommationRepository.findByUserId(userId);
    }

    public List<Recommendation> getActivityRecommendation(String activityid) {
        return recommationRepository.findByActivity_Id(activityid);
    }



}
