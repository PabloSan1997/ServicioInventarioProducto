package com.project.list.products.send.serviceproducts.services.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.list.products.send.serviceproducts.exceptions.MyBadRequestException;
import com.project.list.products.send.serviceproducts.models.dtos.LoginResponse;
import com.project.list.products.send.serviceproducts.models.dtos.UserSecurity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static com.project.list.products.send.serviceproducts.services.utils.JwtProperties.*;

@Service
public class JwtService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Value("${secret.key.data}")
    private String secretKey;
    public LoginResponse generateToken(UserDetails userDetails) throws JsonProcessingException {

        Claims claims = Jwts.claims()
                .add("authorities", objectMapper.writeValueAsString(userDetails.getAuthorities()))
                .build();
        String token = Jwts.builder().signWith(generateKey())
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .compact();
        return LoginResponse.builder().username(userDetails.getUsername()).token(token).build();
    }

    public UserDetails validationToken(String token) throws IOException {

            Claims claims = Jwts.parser().verifyWith(generateKey()).build().parseSignedClaims(token).getPayload();
            String username = claims.getSubject();
            String authoritiesJson = (String) claims.get("authorities");
            Collection<? extends GrantedAuthority> authorities = null;
            authorities = List.of(
                    objectMapper
                            .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedJson.class)
                            .readValue(authoritiesJson.getBytes(), SimpleGrantedAuthority[].class)
            );
            return new UserSecurity(username, "",authorities);
    }

    private SecretKey generateKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
    }
}
