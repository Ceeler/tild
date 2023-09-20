package ru.example.tild.database.structure.User;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_position")
    private String userPosition;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "user_role")
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @Column(name = "status")
    private String status;

//    @OneToOne
//    @JoinColumn(name = "user_active_task")
//    private Task activeTask;

    @ManyToMany(mappedBy = "executors", fetch = FetchType.LAZY)
    private Set<Task> userTasks;

    @Column(name = "user_jwt")
    @OneToMany(mappedBy = "user")
    private Set<JwtToken> tokenList;

    @Column(name = "user_projects")
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private Set<Project> userProjects;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToMany(mappedBy = "author")
    private Set<DirectMessage> directMessages;

    public User(UserSignup userSignup){
        this.firstName = userSignup.getFirstName();
        this.lastName = userSignup.getLastName();
        this.email = userSignup.getEmail();
        this.password = userSignup.getPassword();
        this.username = userSignup.getUsername();
        this.userRole = UserRole.USER;
    }
}
