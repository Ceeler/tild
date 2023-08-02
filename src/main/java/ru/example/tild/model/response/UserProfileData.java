package ru.example.tild.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.tild.database.structure.Project.Project;
import ru.example.tild.database.structure.Task.Task;
import ru.example.tild.database.structure.User.User;

import java.time.Instant;
import java.util.HashSet;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileData {

    private Long id;

    private String name;

    private String surname;

    private String position;

    private String nickname;

    private Task activeTask;

    private String status;

    private HashSet<Task> userTasks;

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
    }

}
