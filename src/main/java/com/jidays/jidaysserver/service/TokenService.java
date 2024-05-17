package com.jidays.jidaysserver.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.JWTVerifier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    private static final String SECRET_KEY = "yourSecretKey";
    private static final long EXPIRATION_TIME = 900_000; // 15 minutes in milliseconds

    public static String generateToken(String userEmail) {
        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + EXPIRATION_TIME);
        return JWT.create()
                .withSubject("User Details")
                .withClaim("email", userEmail)
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public static boolean isValidToken(String token, String userEmail) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .withSubject("User Details")
                    .withClaim("email", userEmail)
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
            return false;
        }
    }

    // Optional: Method to extract information from token
    public static String getEmailFromToken(String token) {
        return JWT.decode(token).getClaim("email").asString();
    }
}
