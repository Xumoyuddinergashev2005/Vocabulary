package org.example.vocabulary.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "pack_word")
public class PackWord {

    @Id
    private Long packId;

    @Id
    private Long wordId;
}

