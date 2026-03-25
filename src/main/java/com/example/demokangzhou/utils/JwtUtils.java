package com.example.demokangzhou.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtUtils {
    // 秘钥
    private static final String SECRET_KEY = "TeamTaskSysSecretKeyForKangzhou";
    // 过期时间：7 天
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7;

    /**
     * 生成 Token
     */
    public static String generateToken(Long userId, String username) {
        return Jwts.builder()
                .claim("userId", userId) // 把用户ID存入 payload
                .claim("username", username)
                .setIssuedAt(new Date()) // 签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 过期时间
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 签名算法和秘钥
                .compact();
    }

    /**
     * 解析 Token
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}