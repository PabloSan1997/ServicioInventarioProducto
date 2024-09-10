package com.project.list.products.send.serviceproducts.services.utils;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

public class JwtProperties {
    public final static SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public final static String AUTHORIZATION = "Authorization";
    public final static String BEARER = "Bearer ";
}
