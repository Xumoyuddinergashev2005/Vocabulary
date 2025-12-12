package org.example.vocabulary.dto;




import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record WordResponseDto(
        String wordEn,
        String wordUz,
        String exampleSentence,
        LocalDateTime  createdAt,
        String audioFile,
        Long categoryId

) {
}
