package org.example.vocabulary.dto;


import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record StudySessionResponseDto(
        Long sessionId,
        Long categoryId,
        LocalDateTime startedAt,
        Boolean completed
) {}
