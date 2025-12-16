package org.example.vocabulary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "pack_word")
public class PackWord {

    @Id
    @Column(name="pack_Id")
    private Long packId;


    @Column(name = "word_Id")
    private Long wordId;


    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name="delete_at")
    private LocalDateTime deleteAt;
}

