package com.token.authenticate.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenUtil {
    public static Claims extractClaims(String token, String key){  //map
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

    public static String getUserName(String token, String key){
        return extractClaims(token, key).get("userName").toString();
    }

    public static boolean isExpired(String token, String secretKey){
        Date expireDate = extractClaims(token, secretKey).getExpiration();
        return expireDate.before(new Date());
    }

    public static String createToken(String username, String Key, long expireTimeMs) {
        Claims claims = Jwts.claims(); // 일종의 map
        claims.put("userName", username);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
                .signWith(SignatureAlgorithm.HS256, Key)
                .compact();
    }
}
