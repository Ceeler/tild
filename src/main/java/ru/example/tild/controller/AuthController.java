package ru.example.tild.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.security.auth.message.AuthException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.example.tild.model.request.FingerprintRequest;
import ru.example.tild.model.request.UserLogin;
import ru.example.tild.model.response.JwtData;
import ru.example.tild.model.response.LoginResponse;
import ru.example.tild.service.AuthService;

@RestController
@AllArgsConstructor
@RequestMapping(path = "auth")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Авторизоваться", description = "Позволяет удалить или добавить баллы пользователю")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserLogin userLogin,
                                               @RequestHeader("User-Agent") String ua
                                               // HttpServletResponse response
    ) throws AuthException {
        return authService.login(userLogin, ua);
    }

    @Operation(summary = "Обновить токены", description = "Позволяет удалить или добавить баллы пользователю")
    @PostMapping("/refresh")
    public ResponseEntity<JwtData> refreshToken(@RequestBody FingerprintRequest fingerprint,
                                                @CookieValue("Refresh-Token") String refreshToken
    ) throws AuthException {
        return authService.refreshTokens(fingerprint.getFingerprint(), refreshToken);
    }

}
