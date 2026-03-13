package com.project.Fitness_App.Controller;

import com.project.Fitness_App.DTOs.LoginRequest;
import com.project.Fitness_App.DTOs.LoginResponse;
import com.project.Fitness_App.DTOs.RegisterRequest;
import com.project.Fitness_App.DTOs.UserResponse;
import com.project.Fitness_App.Entity.user;
import com.project.Fitness_App.Repository.UserRepository;
import com.project.Fitness_App.Security.JwtUtils;
import com.project.Fitness_App.Sevices.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok( userService.register(registerRequest));
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        try {
            Authentication authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            UserDetails userDetails= (UserDetails) authentication.getPrincipal();

            user users=userRepository.findByEmail(userDetails.getUsername())
                    .orElseThrow(()->new RuntimeException("User Not Found"));


            String Token=jwtUtils.generateToken(users.getEmail(),users.getRole());

            return ResponseEntity.ok(new LoginResponse(Token,userService.mapToResponse(users)));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).build();
        }

    }
}
