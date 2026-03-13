package com.project.Fitness_App.ServiceTestes;

import com.project.Fitness_App.DTOs.RegisterRequest;
import com.project.Fitness_App.Repository.UserRepository;
import com.project.Fitness_App.Sevices.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private  UserRepository userRepository;
    @Mock
    private  PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;

    @Test
    void registerTest() {
        RegisterRequest request=new RegisterRequest();
        request.setEmail("som123@gmail.com");
        request.setPassword("12345");
        request.setFirstName("somu");
        request.setLastName("rout");

        when(passwordEncoder.encode("12345")).thenReturn("Encoded12345");
        when(userRepository.save(any())).thenAnswer(invo->invo.getArgument(0));

        var respose=userService.register(request);

        assertEquals("som123@gmail.com",respose.getEmail());
        assertEquals("Encoded12345",respose.getPassword());
        assertEquals("somu",respose.getFirstName());
        assertEquals("rout",respose.getLastName());

        verify(userRepository).save(any());

    }
}