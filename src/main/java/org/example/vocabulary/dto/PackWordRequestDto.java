package org.example.vocabulary.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PackWordRequestDto(
        @NotNull(message = "Cannot be null")
        Long packId
) {
}
