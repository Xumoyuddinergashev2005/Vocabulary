package org.example.vocabulary.dto.response;


import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PackageResponseDto(
        Long id,
        Long categoryId,
        Long levelId,
        Long packNumber,
        LocalDateTime createdAt) {
}
