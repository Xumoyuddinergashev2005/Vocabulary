package org.example.vocabulary.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* User bilan bog‘lanadi */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /* Word bilan bog‘lanadi */
    @ManyToOne
    @JoinColumn(name = "word_id", nullable = false)
    private Word word;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<LessonStep> steps;

    @Enumerated(EnumType.STRING)
    private LessonStatus status;
}
