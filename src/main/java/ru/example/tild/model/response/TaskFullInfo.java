package ru.example.tild.model.response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.example.tild.database.structure.Project.Project;
import ru.example.tild.database.structure.Task.Task;
import ru.example.tild.database.structure.User.User;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskFullInfo {

    private Long id;

    private String taskName;

    private String taskDescription;

    //private Integer estimatedTimeHours;

    private UserPreviewInfo author;

    private Project project;

    private List<UserPreviewInfo> executors;

    //private LocalDate expiredAt;

    private Instant createdAt;

    private Instant updatedAt;

    public TaskFullInfo(Task task){
        this.id = task.getId();
        this.taskName = task.getTaskName();
        this.taskDescription = task.getTaskDescription();
        //this.estimatedTimeHours = task.getEstimatedTimeHours();
        this.author = new UserPreviewInfo(task.getAuthor());
        this.project = task.getProject();
        this.executors = task.getExecutors().stream().map(user -> new UserPreviewInfo(user)).collect(Collectors.toList());
        //this.expiredAt = task.getExpiredAt();
        this.createdAt = task.getCreatedAt();
        this.updatedAt = task.getUpdatedAt();
    }

}
