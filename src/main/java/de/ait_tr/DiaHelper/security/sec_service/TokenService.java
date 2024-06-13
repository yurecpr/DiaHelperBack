package de.ait_tr.DiaHelper.security.sec_service;

import de.ait_tr.DiaHelper.domain.entity.Role;
import de.ait_tr.DiaHelper.domain.entity.User;
import de.ait_tr.DiaHelper.repository.RoleRepository;
import de.ait_tr.DiaHelper.security.AuthInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class TokenService {

    private SecretKey accessKey;
    private SecretKey refreshKey;
    private RoleRepository roleRepository;

    public TokenService(
            @Value("${key.access}") String accessKey,//SecretKey accessKey,
            @Value("${key.refresh}") String refreshKey,//SecretKey refreshKey,
            RoleRepository roleRepository
    ) {
        this.accessKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessKey)); // this.accessKey = accessKey;
        this.refreshKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshKey));  //this.refreshKey = refreshKey;
        this.roleRepository = roleRepository;
    }

    public String generateAccessToken(User user) {
        //текущая дата
        LocalDateTime currentDate = LocalDateTime.now();
        //срок годности accesstoken
        Instant expirationInstant =
                currentDate.plusDays(7).atZone(ZoneId.systemDefault()).toInstant();
        //Преобразование формата для работы с токеном
        Date expirationDate = Date.from(expirationInstant);

        //генерация токен
        return Jwts.builder()
                .subject(user.getEmail())//setSubject()-работает со старой версией//логин
                .expiration(expirationDate)
                .signWith(accessKey)
                .claim("roles", user.getAuthorities())
                .claim("email", user.getEmail())//реальное имя
                .compact();
    }

    public String generateRefreshToken(User user) {
        //текущая дата
        LocalDateTime currentDate = LocalDateTime.now();
        //срок годности refreshtoken
        Instant expirationInstant =
                currentDate.plusDays(30).atZone(ZoneId.systemDefault()).toInstant();
        //Преобразование формата для работы с токеном
        Date expirationDate = Date.from(expirationInstant);

        //генерация токен
        return Jwts.builder()
                .subject(user.getEmail())
                .expiration(expirationDate)
                .signWith(refreshKey)
                .compact();
    }

    //методы которые отдельно проверяет accesstoken и refreshtoken
    public boolean validateAccessToken(String accessToken) {

        return validateToken(accessToken, accessKey);
    }

    public boolean validateRefreshToken(String refreshToken) {

        return validateToken(refreshToken, refreshKey);
    }

    //метод проверки токенов - валидация токенов -будет вызываться только внутри класса
    private boolean validateToken(String token, SecretKey key) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //информация о пользователе из ключа
    public Claims getAccessClaims(String accessToken) {
        return getClaims(accessToken, accessKey);
    }

    public Claims getRefreshClaims(String refreshToken) {
        return getClaims(refreshToken, refreshKey);
    }

    //Объект Claims содержит информацию о пользователе
    private Claims getClaims(String token, SecretKey key) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //объект Claims нужно преобразовать к AuthInfo. преобразование(маппинг) типов
    public AuthInfo generateAuthInfo(Claims claims) {
        String email = claims.getSubject();
        List<LinkedHashMap<String, String>> rolesList = (List<LinkedHashMap<String, String>>) claims.get("roles");
        Set<Role> roles = new HashSet<>();

        for (LinkedHashMap<String, String> roleEntry : rolesList) {
            String roleTitle = roleEntry.get("authority");
            Role role = roleRepository.findByTitle(roleTitle);
            roles.add(role);
        }
        return new AuthInfo(email, roles);
    }

}
