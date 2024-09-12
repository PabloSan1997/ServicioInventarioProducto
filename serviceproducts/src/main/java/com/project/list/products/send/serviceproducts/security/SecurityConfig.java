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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

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
                        .requestMatchers(HttpMethod.GET,
                                "/api/product",
                                "/api/product/search",
                                "/api/product/price-desc",
                                "/api/product/price-asc"
                        ).hasRole(RoleEnum.USER.name())
                        .requestMatchers(HttpMethod.POST, "/api/product").hasRole(RoleEnum.USER.name())
                        .requestMatchers(HttpMethod.PUT,  "/api/product/{id}", "/api/moneda/{id}").hasRole(RoleEnum.USER.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/product/{id}").hasRole(RoleEnum.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/api/user/login").permitAll()
                        .requestMatchers("/", "index.html", "/assets/**").permitAll()
                        .anyRequest().permitAll()
                )
                .cors(c -> c.configurationSource(corsConfigurationSource()))
                .addFilter(new JwtValidationFilter(authenticationManager(), jwtService))
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    CommandLineRunner commandLineRunner(InitialDataService initialDataService){
        return arg -> {
            initialDataService.generateUserInfo();
        };
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("Content-Type", "Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
