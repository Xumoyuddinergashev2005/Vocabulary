package org.example.vocabulary.dto;


import lombok.Builder;

@Builder
public record LevelResponseByCategoryIdDto(
        Long id,
        String name
) {
}
