package com.project.Fitness_App.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authRequest=request.getHeader("Authorization");
        String token=null;
        String email=null;

        if(authRequest!=null&& authRequest.startsWith("Bearer ")){
            token=authRequest.substring(7);
            try {

                email=jwtUtils.extractUserEmail(token);


            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid Token");
            }
        }

        if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=userDetailsService.loadUserByUsername(email);

            if(jwtUtils.isTokenValid(token,email)){
                String roles = jwtUtils.extractroles(token);
                List<GrantedAuthority> authorities = List.of(
                        new SimpleGrantedAuthority("ROLE_" + roles)  );

                UsernamePasswordAuthenticationToken AuthToken=new UsernamePasswordAuthenticationToken(userDetails,null,authorities);
                AuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(AuthToken);

            }

        }


        filterChain.doFilter(request,response);
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().startsWith("/api/auth");
    }
}
