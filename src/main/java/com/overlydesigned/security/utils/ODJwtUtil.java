package com.overlydesigned.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.function.Function;

public final class ODJwtUtil {

    //256 bites long stored in prop file and then hashed
    public static final String SECRET_KEY = "SECRET";

    public static String createJwtToken(String username, Collection<? extends GrantedAuthority> claims) {
        return Jwts.builder()
                .claim("Authority", claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .setAudience("[OD_AUTH_MS-5485, OD_USER_MS-5436]")
                .setIssuer("OD")
                .setId(UUID.randomUUID().toString())
                .compact();
    }

    public static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public static String getUsernameFromToken(String jwtToken) {
        return getClaimFromToken(jwtToken, Claims::getSubject);
    }

    public static Claims getAllClaimsFromToken(String jwtToken) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken).getBody();
    }

    public static Boolean isValidJWT(String jwtToken, UserDetails userDetails) {
        return getUsernameFromToken(jwtToken).equals(userDetails.getUsername()) && !isTokenExpired(jwtToken);
    }

    public static Boolean isTokenExpired(String jwtToken) {
        final Date expiration = getExpirationDateFromToken(jwtToken);
        return expiration.before(new Date());
    }

    public static Date getExpirationDateFromToken(String jwtToken) {
        return getClaimFromToken(jwtToken, Claims::getExpiration);
    }


}
