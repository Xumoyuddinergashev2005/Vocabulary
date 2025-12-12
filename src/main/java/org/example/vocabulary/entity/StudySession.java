package org.example.vocabulary.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "study_session")
public class StudySession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* Qaysi user o‘qiyapti */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /* Qaysi category bo‘yicha trening */
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private LocalDateTime startedAt;

    private LocalDateTime finishedAt;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean completed;
}
