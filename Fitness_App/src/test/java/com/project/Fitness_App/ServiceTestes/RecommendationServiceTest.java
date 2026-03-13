package com.project.Fitness_App.ServiceTestes;

import com.project.Fitness_App.DTOs.RecommendationRequest;
import com.project.Fitness_App.Entity.Activity;
import com.project.Fitness_App.Entity.ActivityType;
import com.project.Fitness_App.Entity.RecommendationsRules;
import com.project.Fitness_App.Entity.user;
import com.project.Fitness_App.Repository.ActivityRepository;
import com.project.Fitness_App.Repository.RecommationRepository;
import com.project.Fitness_App.Repository.RecommendationruleRepository;
import com.project.Fitness_App.Repository.UserRepository;
import com.project.Fitness_App.Sevices.RecommendationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecommendationServiceTest {
    @Mock
    private  UserRepository userRepository;
    @Mock
    private  ActivityRepository activityRepository;
    @Mock
    private  RecommationRepository recommationRepository;
    @Mock
    private RecommendationruleRepository recommendationruleRepository;
    @InjectMocks
    private RecommendationService recommendationService;

    @Test
    void UserNotFoundExceptionThrow(){
        RecommendationRequest request=new RecommendationRequest();
        request.setUserId("1");
        request.setActivityId("5");

        when(userRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,()->recommendationService.generateRecommendation(request));
    }


    @Test
    void ActivityNotFoundExceptionThrow(){
        RecommendationRequest request=new RecommendationRequest();
        request.setUserId("1");
        request.setActivityId("5");

        user users=new user();
        when(userRepository.findById("1")).thenReturn(Optional.of(users));
        when(activityRepository.findById("5")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,()->recommendationService.generateRecommendation(request));

    }
    @Test
    void recommendationGeneratetest(){
        RecommendationRequest request=new RecommendationRequest();
        request.setUserId("1");
        request.setActivityId("5");


        user users=new user();
        users.setId("1");

        Activity activity=new Activity();
        activity.setId("5");
        activity.setDuration(40);
        activity.setCaloriesBurned(300);
        activity.setType(ActivityType.RUNNING);

        RecommendationsRules rule=new RecommendationsRules();
        rule.setMinDuration(20);
        rule.setMaxDuration(40);
        rule.setMinCalories(100);
        rule.setMaxCalories(300);
        rule.setImprovement("Increase Speed");
        rule.setSuggestion("Drink more water");
        rule.setSafety("DO warm up first");


        when(userRepository.findById("1")).thenReturn(Optional.of(users));
        when(activityRepository.findById("5")).thenReturn(Optional.of(activity));
        when(recommendationruleRepository.findRuleByActivityType(activity.getType())).thenReturn(List.of(rule));
        when(recommationRepository.save(any())).thenAnswer(invo->invo.getArgument(0));

        var response = recommendationService.generateRecommendation(request);

        assertEquals("1", response.getUserId());
        assertEquals("5", response.getActivityId());
        assertFalse(response.getImprovements().isEmpty());
        assertFalse(response.getSuggestions().isEmpty());
        assertFalse(response.getSafety().isEmpty());

        verify(recommationRepository).save(any());


    }



}
