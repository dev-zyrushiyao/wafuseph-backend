package com.vtuber.wafuseph_backend.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            // boolean valid = jwtService.isTokenValid(token);
            // System.out.println("Token valid: " + valid);    

            
            try{
                if (jwtService.isTokenValid(token)
                    && SecurityContextHolder.getContext().getAuthentication() == null) {

                    String role = jwtService.extractRole(token);
                    List<SimpleGrantedAuthority> authorities =
                    (role == null || role.isBlank())
                     ? List.of()
                     : List.of(new SimpleGrantedAuthority("ROLE_" + role));

                String subject = jwtService.extractSubject(token);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(subject, null, authorities);

                           //Stored in Authentication Class for controller
                SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
            } catch (Exception ex){
                SecurityContextHolder.clearContext();
            }    
            
        }

        filterChain.doFilter(request, response);
    }


}