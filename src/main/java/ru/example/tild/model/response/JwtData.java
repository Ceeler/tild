package ru.example.tild.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;


@Getter
@AllArgsConstructor
public class JwtData {

    private final String type = "Bearer";
    private String accessToken;
    private String refreshToken;
    private Instant expireAt;

}
