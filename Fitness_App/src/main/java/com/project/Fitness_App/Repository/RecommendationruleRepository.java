package com.project.Fitness_App.Repository;

import com.project.Fitness_App.Entity.ActivityType;
import com.project.Fitness_App.Entity.RecommendationsRules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationruleRepository extends JpaRepository<RecommendationsRules,String> {
    @Query("""
             SELECT rl FROM RecommendationsRules rl WHERE rl.activityType IS NULL OR rl.activityType=:type
            """)
     List<RecommendationsRules>findRuleByActivityType(@Param("type") ActivityType type);
}
