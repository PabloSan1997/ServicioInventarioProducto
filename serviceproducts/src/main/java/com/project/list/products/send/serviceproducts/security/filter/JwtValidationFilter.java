package com.project.list.products.send.serviceproducts.security.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.list.products.send.serviceproducts.exceptions.MyBadRequestException;
import com.project.list.products.send.serviceproducts.models.dtos.ErrorDto;
import com.project.list.products.send.serviceproducts.services.utils.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

import static com.project.list.products.send.serviceproducts.services.utils.JwtProperties.*;

public class JwtValidationFilter extends BasicAuthenticationFilter {
    private final JwtService jwtService;
    public JwtValidationFilter(AuthenticationManager authenticationManager, JwtService jwtService) {
        super(authenticationManager);
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(AUTHORIZATION);

        if(header==null || !header.startsWith(BEARER)){
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace(BEARER, "");

        try{
            UserDetails user = jwtService.validationToken(token);

            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);
        }catch (Exception e){
            HttpStatus status = HttpStatus.BAD_REQUEST;
            ErrorDto errorDto = new ErrorDto(status, e);

            response.setStatus(status.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(new ObjectMapper().writeValueAsString(errorDto));
        }
    }
}
