package ru.example.tild.model.response;

import lombok.*;
import ru.example.tild.database.structure.Project.Project;
import ru.example.tild.database.structure.User.User;

import java.time.Instant;
import java.util.HashSet;

import static java.util.stream.Collectors.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileData {

    private Long id;

    private String name;

    private String surname;

    private String position;

    private String nickname;

    private TaskPreview activeTask;

    private String email;

    private String status;

    private HashSet<TaskPreview> userTasks;

    private HashSet<Project> userProjects;

    private Instant createdAt;

    public UserProfileData(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.position = user.getPosition();
        this.nickname = user.getNickname();
        this.status = user.getStatus();
        this.createdAt = user.getCreatedAt();
        this.email = user.getEmail();
        this.userTasks = new HashSet<>(user.getUserTasks().stream().map(task -> new TaskPreview(task)
        ).collect(toSet()));
    }

}
