package ru.example.tild.database.structure.ProjectMassage;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.example.tild.database.structure.Project.Project;
import ru.example.tild.database.structure.User.User;
import ru.example.tild.database.structure.Comment.Comment;

import java.time.Instant;
import java.util.HashSet;

@Entity
@Table(name = "project_messages")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectMessage {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "project_text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(mappedBy = "projectMessage", fetch = FetchType.LAZY)
    private HashSet<Comment> comments;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;
}
