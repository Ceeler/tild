package ru.example.tild.database.structure.Project;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.example.tild.database.structure.Task.Task;
import ru.example.tild.database.structure.User.User;
import ru.example.tild.database.structure.ProjectMassage.ProjectMessage;

import java.time.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "projects")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_description")
    private String projectDescription;

    @Column(name = "project_tasks")
    @OneToMany(mappedBy = "project")
    private Set<Task> projectTasks;

    @Column(name = "ended_at")
    private LocalDate endedAt;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "user_project")
    private HashSet<User> users;

    @OneToMany(mappedBy = "project")
    private LinkedHashSet<ProjectMessage> projectMessages;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;

}
