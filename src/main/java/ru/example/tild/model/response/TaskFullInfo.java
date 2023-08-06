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


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskFullInfo {

    private Long id;

    private String taskName;

    private String taskDescription;

    private Integer estimatedTimeHours;

    private UserPreviewInfo authorId;

    private Project projectId;

    private UserPreviewInfo responsibleUserId;

    private LocalDate expiredAt;

    private Instant createdAt;

    private Instant updatedAt;

    public TaskFullInfo(Task task){
        this.id = task.getId();
        this.taskName = task.getTaskName();
        this.taskDescription = task.getTaskDescription();
        this.estimatedTimeHours = task.getEstimatedTimeHours();
        this.authorId = new UserPreviewInfo(task.getAuthorId());
        this.projectId = task.getProjectId();
        this.responsibleUserId = new UserPreviewInfo(task.getResponsibleUserId());
        this.expiredAt = task.getExpiredAt();
        this.createdAt = task.getCreatedAt();
        this.updatedAt = task.getUpdatedAt();
    }

}
