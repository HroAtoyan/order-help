package com.platform.config;


import com.platform.entity.UserEntity;

import com.platform.exceptions.userexceptions.UserBadRequestException;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    private final String secret_key = "orderhelpkey";

    private long accessTokenValidity = 300;

    private final JwtParser jwtParser;

    private final String TOKEN_HEADER = "Authorization";

    private final String TOKEN_PREFIX = "Bearer";


    public JwtUtil() {
        this.jwtParser = Jwts.parser().setSigningKey(secret_key);
    }

    public String createToken(UserEntity userEntity) {


        Claims claims = Jwts.claims().setSubject(userEntity.getEmail());
        claims.put("firstName", userEntity.getName());
        claims.put("lastName", userEntity.getSurname());

        Date tokenCreateTime = new Date();
        Date tokenValidation = new Date(tokenCreateTime.getTime() + TimeUnit.SECONDS.toMillis(accessTokenValidity));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(tokenCreateTime)
                .setExpiration(tokenValidation)
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();
    }

    private Claims parsJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();

    }
    public Claims resolveClaims(HttpServletRequest request) {

        try {
            String token = resolveToken(request);
            if (token != null) {
                return parsJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            throw new UserBadRequestException("Token expired was overed");
        } catch (Exception ex) {
            throw new UserBadRequestException("unauthorized");
        }
    }

    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    public boolean validateClaims(Claims claims) {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            throw new UserBadRequestException("Token expired was overed");
        }

    }

}
