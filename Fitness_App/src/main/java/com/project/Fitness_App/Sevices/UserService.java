package com.project.Fitness_App.Sevices;

import com.project.Fitness_App.DTOs.LoginRequest;
import com.project.Fitness_App.DTOs.RegisterRequest;
import com.project.Fitness_App.DTOs.UserResponse;
import com.project.Fitness_App.Entity.UserRole;
import com.project.Fitness_App.Entity.user;
import com.project.Fitness_App.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse register(RegisterRequest request) {

        UserRole role=request.getRole()!=null ? request.getRole() : UserRole.USER;

        user users = user.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();
        user saveUser = userRepository.save(users);
        return mapToResponse(saveUser);
    }

    public UserResponse mapToResponse(user saveUser) {
        UserResponse response =new UserResponse();
        response.setId(saveUser.getId());
        response.setEmail(saveUser.getEmail());
        response.setPassword(saveUser.getPassword());
        response.setFirstName(saveUser.getFirstName());
        response.setLastName(saveUser.getLastName());
        response.setCreateAt(saveUser.getCreateAt());
        response.setUpdatedAt(saveUser.getUpdatedAt());
        return response;
    }


}
