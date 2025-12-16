package org.example.vocabulary.entity;


import jakarta.persistence.*;
import lombok.*;



@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_pack_progress")
public class UserPackProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToOne(optional = false)
    private MyPackage pack;

    private Long currentStage; // 1..4
    private Boolean completed;



}
