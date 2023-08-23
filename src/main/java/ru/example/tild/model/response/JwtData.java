package ru.example.tild.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class JwtData {

    private final String type = "Bearer";
    private String accessToken;
    private String refreshToken;

}
