package org.example.vocabulary.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CategoryResponseDto(
        Long id,
        @NotNull(message = "Name bo'sh bo'lmasligi kerak")
        @NotBlank(message = "Name bo'sh bo'lmasligi kerak")
        String name,
        @NotNull(message = "Description not be empty")
        @NotBlank(message = "Not be empty")
        String description
) {
}
