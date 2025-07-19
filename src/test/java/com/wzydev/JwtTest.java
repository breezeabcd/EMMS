package com.wzydev;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    /**
     * 生成jwt令牌
     * SignatureAlgorithm.HS256是一种签名算法，它用于生成令牌的最后一部分，是为了防止令牌被篡改，保证其安全性
     */
    @Test
    public void testGenerateJwt() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "wzy");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "d3p5ZGV2")  //指定加密算法和密钥，这里的密钥是wzydev经base64编码后得到的字符串
                .addClaims(dataMap)  //添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))  //设置过期时间（毫秒值）。当执行这个方法生成令牌之后，会在1小时之后无法解析
                .compact();  //生成jwt令牌
        System.out.println(jwt);
    }

    /**
     * 解析jwt令牌
     */
    @Test
    public void testParseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJ3enkiLCJleHAiOjE3NDcxMTQ0OTl9.Revl_R08Blvhywe_4k-vNChqUvgd49onVI0UwbaOXAY";
        Claims claims = Jwts.parser()
                .setSigningKey("d3p5ZGV2")  //设定用于验证jwt签名的密钥
                .parseClaimsJws(token)  //对传入的jwt令牌进行解析和验证
                .getBody();  //获取jwt令牌中的自定义信息
        System.out.println(claims);
    }
}
