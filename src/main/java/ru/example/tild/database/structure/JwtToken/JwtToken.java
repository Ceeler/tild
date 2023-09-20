package ru.example.tild.database.structure.JwtToken;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.example.tild.database.structure.User.User;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "jwt_tokens")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtToken {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "refresh_token")
    private UUID refreshToken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    private String useragent;
    private String fingerprint;
    @Column(name = "expires_in")
    private int expiresIn;

    @CreationTimestamp
    private Instant createdAt;

    public JwtToken(UUID refreshToken, User userId, int expiresIn, String fingerprint) {
        this.refreshToken = refreshToken;
        this.userId = userId;
        this.expiresIn = expiresIn;
        this.fingerprint = fingerprint;
    }
}
