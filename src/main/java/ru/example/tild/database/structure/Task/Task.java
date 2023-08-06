package ru.example.tild.database.structure.Task;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.example.tild.database.structure.Project.Project;
import ru.example.tild.database.structure.User.User;
import ru.example.tild.model.request.AddTaskRequest;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_description")
    private String taskDescription;

    @Column(name = "estimated_time_hours")
    private Integer estimatedTimeHours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User authorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project projectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsible_user_id")
    private User responsibleUserId;

    @Column(name = "expired_at")
    private LocalDate expiredAt;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;

    public Task(AddTaskRequest addTaskRequest){
        this.taskName = addTaskRequest.getTaskName();
        this.taskDescription = addTaskRequest.getTaskDescription();
        this.estimatedTimeHours = addTaskRequest.getEstimatedTimeHours();
        this.expiredAt = addTaskRequest.getExpiredAt();
    }
}
