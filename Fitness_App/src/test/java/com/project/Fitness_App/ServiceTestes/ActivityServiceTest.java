package com.project.Fitness_App.ServiceTestes;

import com.project.Fitness_App.DTOs.ActivityRequest;
import com.project.Fitness_App.Entity.Activity;
import com.project.Fitness_App.Entity.ActivityType;
import com.project.Fitness_App.Entity.user;
import com.project.Fitness_App.Repository.ActivityRepository;
import com.project.Fitness_App.Repository.UserRepository;
import com.project.Fitness_App.Sevices.ActivityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActivityServiceTest {

    @Mock
    private  ActivityRepository activityRepository;
    @Mock
    private  UserRepository userRepository;
    @InjectMocks
    private ActivityService activityService;

    @Test
    void UserInvalidExceptions(){
        ActivityRequest request=new ActivityRequest();
        request.setUserId("1");

        when(userRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,()->activityService.trackActivity(request));
    }

    @Test
    void trackActivityTest() {

        ActivityRequest request=new ActivityRequest();
        request.setUserId("1");
        request.setDuration(100);
        request.setCaloriesBurned(200);
        request.setType(ActivityType.RUNNING);



        user users=new user();
        users.setId("1");

        when(userRepository.findById("1")).thenReturn(Optional.of(users));
        when(activityRepository.save(any())).thenAnswer(invo->invo.getArgument(0));

        var response =activityService.trackActivity(request);


        assertEquals("1",response.getUserId());
        assertEquals(100,response.getDuration());
        assertEquals(200,response.getCaloriesBurned());
        assertEquals(ActivityType.RUNNING, response.getType());

        verify(activityRepository).save(any());


    }

    @Test
    void deleteActivityTest() {

        String activityId = "10";

        Activity activity = new Activity();
        activity.setId(activityId);

        when(activityRepository.findById(activityId)).thenReturn(Optional.of(activity));

        activityService.deleteActivity(activityId);

        verify(activityRepository).delete(activity);
    }

}