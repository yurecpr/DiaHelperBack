package de.ait_tr.DiaHelper.security.sec_controller;

import de.ait_tr.DiaHelper.domain.entity.User;
import de.ait_tr.DiaHelper.exception_handling.Response;
import de.ait_tr.DiaHelper.security.sec_dto.RefreshRequestDto;
import de.ait_tr.DiaHelper.security.sec_dto.TokenResponseDto;
import de.ait_tr.DiaHelper.security.sec_service.AuthService;
import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user, HttpServletResponse response) {
        try {
            TokenResponseDto tokenDto = service.login(user);
            Cookie cookie = new Cookie("Access-Token", tokenDto.getAccessToken());
            cookie.setPath("/");

            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return ResponseEntity.ok(tokenDto);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/access")
    public ResponseEntity<Object> getNewAccessToken(@RequestBody RefreshRequestDto request, HttpServletResponse response) {
        try {
            TokenResponseDto tokenDto = service.getAccessToken(request.getRefreshToken());
            Cookie cookie = new Cookie("Access-Token", tokenDto.getAccessToken());
            cookie.setPath("/");
            //http-only cookie
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return ResponseEntity.ok(tokenDto);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/logout")
//    public void logout(HttpServletResponse response) {
//        Cookie cookie = new Cookie("Access-Token", null);
//        cookie.setPath("/");
//        //http-only cookie
//        cookie.setHttpOnly(true);
//        cookie.setMaxAge(0);
//        response.addCookie(cookie);
//    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout(@AuthenticationPrincipal String email, HttpServletRequest request, HttpServletResponse response) {
        try {
            service.logout(email);
            Cookie cookie = new Cookie("Access-Token", null);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(0);
            response.addCookie(cookie);

            return ResponseEntity.ok("Logged out successfully");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public Response updatePassword(@RequestBody User user) throws AuthException {
        service.updatePassword(user);
        return new Response("Please check your email.");
    }
//    public ResponseEntity<Object> updatePassword(@RequestBody User user, HttpServletResponse response) {
//        try {
//            TokenResponseDto tokenDto = service.updatePassword(user);
//            Cookie cookie = new Cookie("Access-Token", tokenDto.getAccessToken());
//            cookie.setPath("/");
//
//            cookie.setHttpOnly(true);
//            response.addCookie(cookie);
//            return ResponseEntity.ok(tokenDto);
//
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
}
