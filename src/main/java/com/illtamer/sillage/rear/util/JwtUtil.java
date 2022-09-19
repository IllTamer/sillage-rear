package com.illtamer.sillage.rear.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 */
public class JwtUtil {

    /**
     * 有效期
     * */
    public static final Long JWT_TTL = 60 * 60 * 1000L;// 60 * 60 *1000  一个小时
    
    /**
     * 设置秘钥明文
     * */
    private static final String JWT_KEY = "04v.5y473q5v3tg04wt0aw";
    
    /**
     * 生成 jwt
     * @param subject token中要存放的数据（json格式）
     */
    public static String createJWT(String subject) {
        return createJWT(subject, null);
    }

    /**
     * 生成 jwt
     * @param subject token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     */
    public static String createJWT(String subject, Long ttlMillis) {
        return createJWT(subject, getUUID(), ttlMillis);
    }

    /**
     * 创建 token
     * @param uuid uuid
     * @param subject token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     */
    public static String createJWT(String uuid, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, uuid); // 设置过期时间
        return builder.compact();
    }

    /**
     * 解析 JWT Token
     * @param jwt JWT Token
     */
    public static Claims parseJWT(String jwt) {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ttlMillis == null){
            ttlMillis = JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)           //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer("IllTamer") // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);
    }

    /**
     * 生成加密后的秘钥 secretKey
     */
    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.getEncoder().encode(JwtUtil.JWT_KEY.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    private static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
