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
import ru.example.tild.model.request.CreateTask;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

//    @Column(name = "estimated_time_hours")
//    private Integer estimatedTimeHours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "user_task",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "task_id")}
    )
    private List<User> executors;

//    @Column(name = "expired_at")
//    private LocalDate expiredAt;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;

    public Task(CreateTask createTask){
        this.taskName = createTask.getTaskName();
        this.taskDescription = createTask.getTaskDescription();
        this.executors = new ArrayList<>();
        //this.estimatedTimeHours = createTask.getEstimatedTimeHours();
        //this.expiredAt = createTask.getExpiredAt();
    }

    public void addExecutor(User executor){
        executors.add(executor);
    }

}
