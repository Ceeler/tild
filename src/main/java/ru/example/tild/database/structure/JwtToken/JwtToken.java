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
@Builder
public class JwtToken {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "refresh_token")
    private UUID refreshToken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String useragent;
    private String fingerprint;
    @Column(name = "expires_in")
    private Long expiresIn;

    @CreationTimestamp
    private Instant createdAt;

    public JwtToken(String refreshToken, User user, Long expiresIn, String fingerprint) {
        this.refreshToken = UUID.fromString(refreshToken);
        this.user = user;
        this.expiresIn = expiresIn;
        this.fingerprint = fingerprint;
    }

}
