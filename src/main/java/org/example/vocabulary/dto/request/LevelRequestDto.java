package org.example.vocabulary.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record LevelRequestDto(
        @NotNull(message = "Cannot be empty")
        @NotBlank(message = "Cannot be empty")
        String name,
        @NotNull(message = "Cannot be empty")
        Long categoryId

) {
}
