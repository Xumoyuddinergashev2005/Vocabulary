package org.example.vocabulary.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "lesson_step")
public class LessonStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* Qaysi darsga tegishli */
    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StepType stepType;

    private Boolean attempt1Correct;
    private Boolean attempt2Correct;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean completed;
}
