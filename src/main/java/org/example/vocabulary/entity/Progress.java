package org.example.vocabulary.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "progress")
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* Foydalanuvchi bilan bog‘liq */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /* So‘z bilan bog‘liq */
    @ManyToOne
    @JoinColumn(name = "word_id", nullable = false)
    private Word word;

    private Integer masteryLevel; // 0–100

    private LocalDateTime lastActivity;
}
