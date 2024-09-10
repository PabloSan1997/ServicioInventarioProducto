package com.project.list.products.send.serviceproducts.security;

import com.project.list.products.send.serviceproducts.models.enums.RoleEnum;
import com.project.list.products.send.serviceproducts.security.filter.JwtValidationFilter;
import com.project.list.products.send.serviceproducts.services.utils.InitialDataService;
import com.project.list.products.send.serviceproducts.services.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->auth
                        .requestMatchers(HttpMethod.GET, "/api/product").hasRole(RoleEnum.USER.name())
                        .requestMatchers(HttpMethod.POST, "/api/user/login").permitAll()
                        .anyRequest().permitAll()
                )
                .addFilter(new JwtValidationFilter(authenticationManager(), jwtService))
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    CommandLineRunner commandLineRunner(InitialDataService initialDataService){
        return arg -> {
            initialDataService.generateRole();
        };
    }
}
