package com.swapp.swapp.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.swapp.swapp.security.filter.JWTAuthenticationFilter;
import com.swapp.swapp.security.filter.JWTAuthorizationFilter;

@Configuration
public class SpringConfig {

    private CustomAuthenticationManager customAuthenticationManager;

    public SpringConfig(CustomAuthenticationManager customAuthenticationManager){
        this.customAuthenticationManager = customAuthenticationManager;
    }

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        JWTAuthenticationFilter authenticationFilter = new JWTAuthenticationFilter(customAuthenticationManager, jwtSecret);
        authenticationFilter.setFilterProcessesUrl("/login");

        http
            .cors(cors -> {}) 
            .csrf(csfr -> csfr.disable())
            // .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
            .authorizeHttpRequests(request -> request
                .requestMatchers("/login").permitAll()
                .requestMatchers("/signup").permitAll()
                .requestMatchers( "/api/v1/users").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/users").authenticated()
                .anyRequest().authenticated()
            )
            .addFilter(authenticationFilter)
            .addFilterAfter(new JWTAuthorizationFilter(jwtSecret), JWTAuthenticationFilter.class)
            .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
            return http.build();
    }

}