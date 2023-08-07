package ru.example.tild.model.response;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.example.tild.database.structure.ProjectMassage.ProjectMessage;
import ru.example.tild.database.structure.Task.Task;
import ru.example.tild.database.structure.User.User;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class ProjectFullInfo {

    private Long id;

    private String projectName;

    private String projectDescription;

    private HashSet<Task> projectTasks;

    private HashSet<User> projectParticipants;

    private LinkedHashSet<ProjectMessage> projectMessages;

    private Instant createdAt;

    private Instant updatedAt;

    private LocalDate endedAt;

}
