package ru.example.tild.database.structure.DirectMessage;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.example.tild.database.structure.User.User;

import java.time.Instant;

@Entity
@Table(name = "direct_messages")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DirectMessage {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "message_text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @CreationTimestamp
    private Instant createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DirectMessage that = (DirectMessage) o;

        if (!id.equals(that.id)) return false;
        return text.equals(that.text);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + text.hashCode();
        return result;
    }
}
