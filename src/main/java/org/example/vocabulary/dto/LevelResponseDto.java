package org.example.vocabulary.dto;

import lombok.Builder;

@Builder
public record LevelResponseDto(
        Long id,
        String  name,
        Long categoryId
) {
}
