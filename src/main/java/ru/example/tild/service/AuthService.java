package ru.example.tild.service;

import jakarta.security.auth.message.AuthException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.tild.database.structure.JwtToken.JwtToken;
import ru.example.tild.database.structure.JwtToken.JwtTokenRepository;
import ru.example.tild.database.structure.User.User;
import ru.example.tild.model.request.UserLogin;
import ru.example.tild.model.response.JwtData;
import ru.example.tild.util.JwtUtils;

@Service
@AllArgsConstructor
public class AuthService {

    final JwtUtils jwtUtils;

    final UserService userService;

    final JwtTokenRepository jwtTokenRepository;
    public JwtData login(UserLogin userLogin) throws AuthException {
        User user = userService.findByEmail(userLogin.getEmail())
                .orElseThrow(() -> new AuthException("Пользователь не найден"));
        if(user.getPassword().equals(userLogin.getPassword())){
            String accessToken = jwtUtils.generateAccessToken(user);
            String refreshToken = jwtUtils.generateAccessToken(user);
            JwtToken jwtToken = new JwtToken();
            //jwtTokenRepository.save();
            return new JwtData("","");
        }else {
            throw new AuthException("Не правильный пароль");
        }
    }

}