package org.example.vocabulary.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "study_session_word")
public class StudySessionWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* Qaysi sessiyaga tegishli */
    @ManyToOne
    @JoinColumn(name = "study_session_id", nullable = false)
    private StudySession session;

    /* Qaysi word bo‘yicha trening qilinmoqda */
    @ManyToOne
    @JoinColumn(name = "word_id", nullable = false)
    private Word word;

    /* 5 bosqichning natijalari */
    private Boolean chooseWordAttempt1;
    private Boolean chooseWordAttempt2;

    private Boolean chooseTranslationAttempt1;
    private Boolean chooseTranslationAttempt2;

    private Boolean constructWordAttempt1;
    private Boolean constructWordAttempt2;

    private Boolean writeWordAttempt1;
    private Boolean writeWordAttempt2;

    private Boolean pronounceWordAttempt1;
    private Boolean pronounceWordAttempt2;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean completed; // shu so‘z bo‘yicha hamma step tugadimi
}
