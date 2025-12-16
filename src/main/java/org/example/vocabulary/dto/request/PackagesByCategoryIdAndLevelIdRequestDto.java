package org.example.vocabulary.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PackagesByCategoryIdAndLevelIdRequestDto(
        @NotNull(message = "Cannot be null")
        Long categoryId,
        @NotNull(message = "Cannot be null")
        Long levelId
) {
}
