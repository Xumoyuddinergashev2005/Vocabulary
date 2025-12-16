package org.example.vocabulary.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "user_word_result")
public class UserWordResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToOne(fetch =FetchType.LAZY)
    private MyPackage pack;

    @ManyToOne
    private Word word;

    private Long stage; // 2,3,4

    private Integer attemptNumber; // 1 yoki 2

    private Boolean correct;
}
