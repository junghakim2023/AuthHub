package com.jha.authhub.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@PropertySource("classpath:device.properties")
@Component
public class JWTManager {

    @Value("${device.JWT_SECET_KEY}")
    private String jwtSecreteKey;
    private final String jwtPrefix = "Bearer ";
    private JwtParser jwtParser = null;

    public String createAccessToken(Long userIndex, String userName) {
        Date now = new Date();
        try {
            byte[] secretBytes = jwtSecreteKey.getBytes(StandardCharsets.UTF_8);
            return Jwts.builder()
                    .setHeaderParam("type", "jwt")
                    .claim("userName", userName)
                    .claim("userIdx", userIndex)
                    .setIssuedAt(now)
                    .setExpiration(new Date(System.currentTimeMillis() + 1000*5))//(12 * 60 * 60 * 1000)))  // 12h
                    .signWith(SignatureAlgorithm.HS256, secretBytes)
                    .compact();
        }catch (Exception e){
            System.out.println("error in createAccessToken : "+ e.getMessage());
        }
        return null;
    }

    public String createTokenKey(Long userIndex) {
        Date now = new Date();
        try {
            String tokenKeySecrete = "tokenKeytokenKeytokenKeytokenKey";
            byte[] secretBytes = tokenKeySecrete.getBytes(StandardCharsets.UTF_8);
            return Jwts.builder()
                    .setHeaderParam("type", "jwt")
                    .claim("userIdx", userIndex)
                    .setIssuedAt(now)
                    .signWith(SignatureAlgorithm.HS256, secretBytes)
                    .compact();
        }catch (Exception e){
            System.out.println("error in createAccessToken : "+ e.getMessage());
        }
        return null;
    }

    public Boolean checkValidToken(String token) {
        return (null != resolveToken(token));
    }

    public Claims resolveToken(String token) {
        if (token == null)
            return null;

        try {
            if (jwtParser == null)
                jwtParser = Jwts.parser().setSigningKey(jwtSecreteKey.getBytes(StandardCharsets.UTF_8));
        }catch (Exception e){
            System.out.println("error in jwtParser : "+ e.getMessage());
        }

        try {
            return jwtParser.parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            System.out.println("error in ExpiredJwtException : "+ e.getMessage());
        } catch (Exception e) {
            System.out.println("error in resolveToken Exception : "+ e.getMessage());
        }
        return null;
    }

    public String createRefreshToken(Long userIdx) {
        Date now = new Date();

        try {
            byte[] secretBytes = jwtSecreteKey.getBytes(StandardCharsets.UTF_8);
            return Jwts.builder()
                    .setHeaderParam("type", "jwt")
                    .claim("userIdx", userIdx)
                    .setIssuedAt(now)
                    .setExpiration(new Date(System.currentTimeMillis() + (60 * 60 * 24 * 7 * 1000))) // 1 week
                    .signWith(SignatureAlgorithm.HS256, secretBytes)
                    .compact();
        } catch (Exception e) {
            System.out.println("error in createRefreshToken Exception : "+ e.getMessage());
        }
        return null;
    }
}

