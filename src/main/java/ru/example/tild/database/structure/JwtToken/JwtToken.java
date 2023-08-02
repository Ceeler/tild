package ru.example.tild.database.structure.JwtToken;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.tild.database.structure.User.User;

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
    private String refreshToken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;
}
