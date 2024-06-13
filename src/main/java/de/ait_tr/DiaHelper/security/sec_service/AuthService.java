package de.ait_tr.DiaHelper.security.sec_service;

import de.ait_tr.DiaHelper.domain.entity.User;
import de.ait_tr.DiaHelper.security.AuthInfo;
import de.ait_tr.DiaHelper.security.sec_dto.TokenResponseDto;
import de.ait_tr.DiaHelper.service.interfaces.EmailService;
import de.ait_tr.DiaHelper.service.interfaces.UserService;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private UserService userService;
    private EmailService emailService;
    private TokenService tokenService;
    private Map<String, String> refreshStorage;
    private BCryptPasswordEncoder encoder;

    public AuthService(UserService userService, EmailService emailService, TokenService tokenService, BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.emailService = emailService;
        this.tokenService = tokenService;
        this.encoder = encoder;
        this.refreshStorage = new HashMap<>();
    }

//    public TokenResponseDto login(@NonNull User inboundUser) throws AuthException {
//        String username = inboundUser.getUsername();
//        User foundUser = (User) userService.loadUserByUsername(username);
//
//        if (encoder.matches(inboundUser.getPassword(), foundUser.getPassword())) {
//            String accessToken = tokenService.generateAccessToken(foundUser);
//            String refreshToken = tokenService.generateRefreshToken(foundUser);
//            refreshStorage.put(username, refreshToken);
//            return new TokenResponseDto(accessToken, refreshToken);
//        } else {
//            throw new AuthException("Password is incorrect");
//        }
//    }

    public TokenResponseDto login(@NonNull User inboundUser) throws AuthException {
        String email = inboundUser.getEmail();
        User foundUser = (User) userService.loadUserByEmail(email);

        if (encoder.matches(inboundUser.getPassword(), foundUser.getPassword())) {
            String accessToken = tokenService.generateAccessToken(foundUser);
            String refreshToken = tokenService.generateRefreshToken(foundUser);
            refreshStorage.put(email, refreshToken);
            return new TokenResponseDto(accessToken, refreshToken);
        } else {
            throw new AuthException("Password is incorrect");
        }
    }
    public void logout(@NonNull String email) {
        refreshStorage.remove(email);
    }
    public  void updatePassword (@NonNull User inboundUser) throws AuthException {
        String email = inboundUser.getEmail();
        User foundUser = (User) userService.loadUserByEmail(email);
        userService.updatePassword(foundUser);

//        if (encoder.matches(inboundUser.getPassword(), foundUser.getPassword())) {
//            String accessToken = tokenService.generateAccessToken(foundUser);
//            String refreshToken = tokenService.generateRefreshToken(foundUser);
//            refreshStorage.put(email, refreshToken);
//            return new TokenResponseDto(accessToken, refreshToken);
//        } else {
//            throw new AuthException("Password is incorrect");
//        }
    }

    public TokenResponseDto getAccessToken(@NonNull String inboundRefreshToken) {
        Claims refreshClaims = tokenService.getRefreshClaims(inboundRefreshToken);
        String username = refreshClaims.getSubject();
        String savedRefreshToken = refreshStorage.get(username);

        // if (savedRefreshToken != null && savedRefreshToken.equals(inboundRefreshToken)) {
        if (inboundRefreshToken.equals(savedRefreshToken)) {
            User user = (User) userService.loadUserByUsername(username);
            String accessToken = tokenService.generateAccessToken(user);
            return new TokenResponseDto(accessToken, null);
        }
        return new TokenResponseDto(null,null);
    }

    public AuthInfo getAuthInfo(){

        return (AuthInfo) SecurityContextHolder.getContext().getAuthentication();
    }
}

