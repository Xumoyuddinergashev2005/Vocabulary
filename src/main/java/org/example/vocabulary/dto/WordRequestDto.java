package org.example.vocabulary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record WordRequestDto(
        @NotNull(message = "Cannot be empty")
        @NotBlank(message = "Cannot be empty")
        String wordEn,
        @NotNull(message = "Cannot be empty")
        @NotBlank(message = "Cannot be empty")
        String wordUz,
        @NotNull(message = "Cannot be empty")
        @NotBlank(message = "Cannot be empty")
        String exampleSentence,
        @NotNull(message = "Cannot be empty")
        @NotBlank(message = "Cannot be empty")
        String audioFile,
        @NotNull(message = "Cannot be empty")
        @NotBlank(message = "Cannot be empty")
        Long categoryId
) {
}
