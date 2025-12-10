package org.example.vocabulary.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryRequestDto(
        @NotNull(message = "Name bo'sh bo'lmasligi kerak")
        @NotBlank(message = "Name bo'sh bo'lmasligi kerak")
        String name,
        @NotNull(message = "Description not be empty")
        @NotBlank(message = "Not be empty")
        String description
) {
}
