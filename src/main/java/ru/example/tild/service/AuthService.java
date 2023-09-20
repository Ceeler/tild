package ru.example.tild.service;

import jakarta.security.auth.message.AuthException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.example.tild.database.structure.JwtToken.JwtToken;
import ru.example.tild.database.structure.JwtToken.JwtTokenRepository;
import ru.example.tild.database.structure.User.User;
import ru.example.tild.model.JwtAuthentication;
import ru.example.tild.model.request.UserLogin;
import ru.example.tild.model.response.JwtData;
import ru.example.tild.util.JwtUtils;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {
    private final JwtUtils jwtUtils;
    private final UserService userService;

    private final JwtTokenRepository jwtTokenRepository;

    private final PasswordEncoder passwordEncoder;

    private static final int REFRESH_TOKEN_TIME = 1*60*60*24*30;

    public ResponseEntity<JwtData> login(UserLogin userLogin, String ua) throws AuthException {
        User user = userService.findByEmail(userLogin.getEmail())
                .orElseThrow(() -> new AuthException("Пользователь не найден"));

        if(passwordEncoder.matches(userLogin.getPassword(), user.getPassword())) {
            String accessToken = jwtUtils.generateAccessToken(user);
            UUID refreshToken = jwtUtils.generateRefreshToken();
            Long refreshExpire = Instant.now().plusSeconds(REFRESH_TOKEN_TIME).getEpochSecond();
            JwtToken jwtToken = JwtToken.builder()
                    .refreshToken(refreshToken)
                    .expiresIn(refreshExpire)
                    .fingerprint(userLogin.getFingerprint())
                    .useragent(ua)
                    .user(user)
                    .build();
            jwtTokenRepository.save(jwtToken);

            HttpCookie cookie = ResponseCookie.from("Refresh-Token", refreshToken.toString())
                    .httpOnly(true)
                    .maxAge(refreshExpire).build();
//                    .domain(".tild.space")
//                    .path("/auth").build();

            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body(new JwtData(accessToken, refreshToken.toString()));
        } else {
            throw new AuthException("Не правильный пароль");
        }
    }

    @Transactional
    public ResponseEntity<JwtData> refreshTokens(String fingerprint, String refreshToken) throws AuthException {

        UUID uuidRefreshToken = UUID.fromString(refreshToken);

        JwtToken jwtTokenOld = jwtTokenRepository.findJwtTokenByRefreshToken(uuidRefreshToken);

        jwtTokenRepository.deleteJwtTokenByRefreshToken(uuidRefreshToken);

        if(jwtTokenOld == null){
            throw new AuthException("Не валидный рефреш токен");
        }
        if (!jwtTokenOld.getFingerprint().equals(fingerprint)) {
            jwtTokenRepository.deleteJwtTokenByRefreshToken(uuidRefreshToken);
            throw new AuthException("Не известный идентификатор устройства");
        }
        if(Instant.ofEpochSecond(jwtTokenOld.getExpiresIn()).isBefore(Instant.now())){
            jwtTokenRepository.deleteJwtTokenByRefreshToken(uuidRefreshToken);
            throw new AuthException("Время жизни токена истекло");
        }

        UUID newRefreshToken = jwtUtils.generateRefreshToken();
        Long newExpire = Instant.now().plusSeconds(REFRESH_TOKEN_TIME).getEpochSecond();
        JwtToken newJwtToken = JwtToken.builder()
                .refreshToken(newRefreshToken)
                .expiresIn(newExpire)
                .fingerprint(jwtTokenOld.getFingerprint())
                .useragent(jwtTokenOld.getFingerprint())
                .user(jwtTokenOld.getUser())
                .build();

        String accessToken = jwtUtils.generateAccessToken(jwtTokenOld.getUser());

        jwtTokenRepository.save(newJwtToken);

        HttpCookie cookie = ResponseCookie.from("Refresh-Token", newRefreshToken.toString())
                .httpOnly(true)
                .maxAge(newExpire)
                .domain(".tild.space")
                .path("/auth").build();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new JwtData(accessToken, newRefreshToken.toString()));
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

}