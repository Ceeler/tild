package ru.example.tild.database.structure.JwtToken;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {

    Integer deleteJwtTokenByRefreshToken(UUID refreshToken);

    @EntityGraph(attributePaths = {"user"})
    JwtToken findJwtTokenByRefreshToken(UUID refreshToken);
}
