package com.project.Fitness_App.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false,foreignKey = @ForeignKey(name ="fk_recommendation_user"))
    @JsonIgnore
    private user user;

    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false,foreignKey = @ForeignKey(name ="fk_recommendation_activity"))
    @JsonIgnore

    private Activity activity;
    @Enumerated(EnumType.STRING)
    private ActivityType type;
    @Column(length = 2000)
    private String recommendation;
    @ElementCollection
    @Column(name = "improvement")
    private List<String> improvements = new ArrayList<>();
    @ElementCollection
    @Column(name = "suggestion")
    private List<String> suggestions = new ArrayList<>();
    @ElementCollection
    @Column(name = "safety")
    private List<String> safety = new ArrayList<>();
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;

}
