package com.project.Fitness_App.Security;

import com.project.Fitness_App.Entity.user;
import com.project.Fitness_App.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsPrincipal implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {

        user users=userRepository.findByEmail(useremail).orElseThrow(()->new RuntimeException("User Not Found"));
        if(users==null){
             throw new RuntimeException("user not found"+useremail);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(users.getEmail())
                .password(users.getPassword())
                .roles(users.getRole().name())
                .build();
    }
}
