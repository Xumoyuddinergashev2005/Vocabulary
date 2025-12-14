package org.example.vocabulary.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "user_stage_attempt")
public class UserStageAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToOne
    private Package pack;

    private Long stage; // 2,3,4

    private Long attemptNumber; // 1 yoki 2

    private Boolean passed;
}
