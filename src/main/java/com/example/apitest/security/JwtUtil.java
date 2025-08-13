package com.example.apitest.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.issuer:api-test}")
    private String issuer;

    @Value("${app.jwt.expiration:3600000}")
    private long expirationMs;

    public String generateToken(String subject) {
        Algorithm alg = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationMs))
                .sign(alg);
    }

    public String validateAndGetSubject(String token) {
        try {
            Algorithm alg = Algorithm.HMAC256(secret);
            DecodedJWT jwt = JWT.require(alg).withIssuer(issuer).build().verify(token);
            return jwt.getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}
