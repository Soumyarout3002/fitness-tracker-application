package com.project.Fitness_App.Repository;

import com.project.Fitness_App.Entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommationRepository extends JpaRepository<Recommendation,String> {
    List<Recommendation> findByUserId(String userId);

    List<Recommendation> findByActivity_Id(String activityid);
}
