package com.project.Fitness_App.Repository;

import com.project.Fitness_App.Entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<user,String> {
    Optional<user> findByEmail(String email);
}
