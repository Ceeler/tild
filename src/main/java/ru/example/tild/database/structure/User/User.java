package ru.example.tild.database.structure.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import ru.example.tild.database.enums.UserRole;
import ru.example.tild.database.structure.DirectMessage.DirectMessage;
import ru.example.tild.database.structure.Task.Task;
import ru.example.tild.database.structure.JwtToken.JwtToken;
import ru.example.tild.database.structure.Project.Project;
import ru.example.tild.model.request.UserSignUpRequest;

import java.time.Instant;
import java.util.HashSet;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
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
    private HashSet<Task> userTasks = new HashSet<>();

    @Column(name = "user_tokens")
    @OneToMany(mappedBy = "userId")
    private HashSet<JwtToken> tokenList;

    @Column(name = "user_projects")
    @ManyToMany
    private HashSet<Project> userProjects;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @OneToMany(mappedBy = "authorId")
    private HashSet<DirectMessage> directMessages;

    public User(UserSignUpRequest userSignUpRequest){
        this.name = userSignUpRequest.getName();
        this.surname = userSignUpRequest.getSurname();
        this.position = userSignUpRequest.getPosition();
        this.password = userSignUpRequest.getPassword();
        this.nickname = userSignUpRequest.getNickName();
        this.userRole = userSignUpRequest.getUserRole();
    }
}
