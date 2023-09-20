package ru.example.tild.model.response;

import lombok.*;
import ru.example.tild.database.structure.Project.Project;
import ru.example.tild.database.structure.Task.Task;
import ru.example.tild.database.structure.User.User;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

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
        this.name = user.getFirstName();
        this.surname = user.getLastName();
        this.position = user.getUserPosition();
        this.nickname = user.getUsername();
        this.status = user.getStatus();
        this.createdAt = user.getCreatedAt();
        this.email = user.getEmail();
        Set<Task> tasks = user.getUserTasks();
        //TODO make another
        if(tasks != null) {
        this.userTasks = new HashSet<>(tasks.stream().map(task -> new TaskPreview(task)
        ).collect(toSet()));
        } else {
            this.userTasks = new HashSet<>();
        }
    }

}
