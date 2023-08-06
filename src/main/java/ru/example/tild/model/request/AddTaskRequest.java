package ru.example.tild.model.request;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.tild.database.structure.Project.Project;
import ru.example.tild.database.structure.User.User;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddTaskRequest {

    private String taskName;

    private String taskDescription;

    private Long authorId;

    private Long projectId;

    private Long responsibleUserId;

    private LocalDate expiredAt;

    private Integer estimatedTimeHours;

}
