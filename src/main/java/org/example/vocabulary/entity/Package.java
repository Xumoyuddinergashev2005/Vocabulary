package org.example.vocabulary.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "package")
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Category category;

    @ManyToOne(optional = false)
    private Level level;

    @Column(nullable = false)
    private Long packNumber; // 1,2,3
}
