package ru.example.tild.database.structure.User;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.example.tild.database.enums.UserRole;
import ru.example.tild.database.structure.DirectMessage.DirectMessage;
import ru.example.tild.database.structure.Task.Task;
import ru.example.tild.database.structure.JwtToken.JwtToken;
import ru.example.tild.database.structure.Project.Project;
import ru.example.tild.model.request.UserSignup;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "user_name")
    private String name;

    @Column(name = "user_surname")
    private String surname;

    @Column(name = "user_position")
    private String position;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname", unique = true)
    private String nickname;

    @Column(name = "user_role")
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @Column(name = "status")
    private String status;

    @OneToOne
    @JoinColumn(name = "user_active_task")
    private Task activeTask;

    @OneToMany(mappedBy = "responsibleUserId")
    private Set<Task> userTasks;

    @Column(name = "user_tokens")
    @OneToMany(mappedBy = "userId")
    private Set<JwtToken> tokenList;

    @Column(name = "user_projects")
    @ManyToMany
    private Set<Project> userProjects;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @OneToMany(mappedBy = "authorId")
    private Set<DirectMessage> directMessages;

    public User(UserSignup userSignup){
        this.name = userSignup.getName();
        this.surname = userSignup.getSurname();
        this.email = userSignup.getEmail();
        this.password = userSignup.getPassword();
        this.nickname = userSignup.getNickName();
        this.userRole = UserRole.USER;
    }
}
