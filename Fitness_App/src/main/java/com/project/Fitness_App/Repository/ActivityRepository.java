package com.project.Fitness_App.Repository;

import com.project.Fitness_App.Entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,String> {


    List<Activity> findByUserId(String userId);
}
