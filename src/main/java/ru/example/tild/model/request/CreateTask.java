package ru.example.tild.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTask {

    private String taskName;

    private String taskDescription;

    private Long authorId;

    private Long projectId;

    private Long responsibleUserId;

    private LocalDate expiredAt;

    private Integer estimatedTimeHours;

}
