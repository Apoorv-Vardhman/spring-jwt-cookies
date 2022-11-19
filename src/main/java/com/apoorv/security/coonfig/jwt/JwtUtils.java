package com.apoorv.security.coonfig.jwt;

import com.apoorv.security.entities.Consumer;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Apoorv Vardhman
 * @Github Apoorv-Vardhman
 * @LinkedIN apoorv-vardhman
 */
@Component
@Slf4j
public class JwtUtils {
    private final String jwtSecret="jwtSecret@#4532";

    private final String jwtCookie="jwt-security";


    public ResponseCookie generateJwtCookie(Consumer user) {
        String jwt = generateTokenFromUsername(user.getEmail());
        return generateCookie(jwtCookie, jwt, "/api");
    }

    public ResponseCookie generateRefreshJwtCookie(String refreshToken) {
        String jwtRefreshCookie = "jwt-security-refresh";
        return generateCookie(jwtRefreshCookie, refreshToken, "/api/RefreshToken");
    }

    public String getJwtFromCookies(HttpServletRequest request) {
        return getCookieValueByName(request, jwtCookie);
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }catch (Exception e) {
            log.error("JWT exception {}", e.getMessage());
        }
        return false;
    }

    public String generateTokenFromUsername(String username) {
        long jwtExpirationMs = 3600000;
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    private ResponseCookie generateCookie(String name, String value, String path) {
        return ResponseCookie.from(name, value).path(path).maxAge(24 * 30 * 60 * 60).httpOnly(true).build();
    }

    private String getCookieValueByName(HttpServletRequest request, String name) {
        Cookie cookie = WebUtils.getCookie(request, name);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }
}
