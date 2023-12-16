package com.lilin.springsecurity.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * Created by LiLin on 2022/7/6/17:39:46
 *
 * JWT工具类
 */
public class JWTUtil {
    //有效期
    public static final Long JWT_TTL = 60*60*1000L; //一个小时

    //设置密钥明文
    public static final String JWT_KEY = "123abc";

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    //生成JWT
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID()); //设置过期时间
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalkey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JWTUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)	                            //唯一的ID
                .setSubject(subject)	                    //主题 可以是JSON数据
                .setIssuer("lilin")		                    //签发者
                .setIssuedAt(now)	                        //签发时间
                .signWith(signatureAlgorithm, secretKey)    //使用HS256对称加密算法签名，第二个参数为密钥
                .setExpiration(expDate);
    }

    //创建token
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id); //设置注册时间
        return builder.compact();
    }

    public static void main(String[] args) {
        String token = "jdiwecvcvdsjcm,ncdlsjcwejcndsue19i01rerfcherjkldf8j2390rferhnfcjlwefchn1269387ryduewhf30fuy34fy8ofhuioewfh38fophjwiedfcnjwfuy348fyu239i9023fujh34gh43o";
        Claims claims = parseJWT(token);
        System.out.println(claims);
    }

    //生成加密后的密钥
    public static SecretKey generalkey() {
        byte[] encodeKey = Base64.getDecoder().decode(JWTUtil.JWT_KEY);
        return new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");
    }

    //解析
    public static Claims parseJWT(String jwt) {
        SecretKey secretKey = generalkey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
