package org.example.vocabulary.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "word")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private String wordEn;

    @Column(nullable = false)
    private String wordUz;

    private String exampleSentence;

    private String audioFile; // audio fayl manzili

    @OneToMany(mappedBy = "word", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Lesson> lessons;
}
