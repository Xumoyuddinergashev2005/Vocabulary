package org.example.vocabulary.dto;




import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record WordResponseDto(
        String wordEn,
        String wordUz,
        String exampleSentence,
        LocalDateTime  createAt,
        String audioFile,
        Long categoryId

) {
}
