package edu.whu.learneur.utils;

import edu.whu.learneur.constant.LearneurConst;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 解析和验证JWT Token的工具类
 */
@Component
public class JwtTokenUtils {
    /**
     * token过期时间
     */


    /**
     * token初识密钥
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * 从Token中获得Claims
     * @param token     令牌
     * @return
     */
    public Claims getClaimFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 生成Token
     * @param userDetails   用户信息
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + LearneurConst.JWT_EXPIRED_TIME))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 对Token进行验证
     * @param token         令牌
     * @param userDetails   用户信息
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        return validateClaim(getClaimFromToken(token), userDetails);
    }

    /**
     * 对Claims进行验证
     * @param claims        验证
     * @param userDetails   用户信息
     * @return
     */
    public boolean validateClaim(Claims claims, UserDetails userDetails) {
        return Objects.nonNull(userDetails) &&
                claims.getSubject().equals(userDetails.getUsername()) &&
                !claims.getExpiration().before(new Date());
    }

    public boolean canTokenBeRefreshed(String token) {
        return (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public boolean isTokenExpired(String token) {
        Claims claims = getClaimFromToken(token);
        return claims.getExpiration().before(new Date());
    }

    private Boolean ignoreTokenExpiration(String token) {
        return false;
    }
}
