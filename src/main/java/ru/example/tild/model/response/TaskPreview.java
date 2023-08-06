package ru.example.tild.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.tild.database.structure.Task.Task;

import java.time.LocalDate;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskPreview {

    private Long id;

    private String taskName;

    private Integer estimatedTimeHours;

    private UserPreviewInfo authorId;

    private LocalDate expiredAt;

    public TaskPreview(Task task){
        this.id = task.getId();
        this.taskName = task.getTaskName();
        this.estimatedTimeHours = task.getEstimatedTimeHours();
        this.authorId = new UserPreviewInfo(task.getAuthorId());
        this.expiredAt = task.getExpiredAt();
    }
}
