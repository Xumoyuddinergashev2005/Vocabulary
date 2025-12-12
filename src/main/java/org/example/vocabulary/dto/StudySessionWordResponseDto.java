package org.example.vocabulary.dto;

import lombok.Builder;

@Builder
public record StudySessionWordResponseDto(
        Long wordId,
        String wordEn,
        String wordUz,
        Boolean chooseWordAttempt1,
        Boolean chooseWordAttempt2,
        Boolean chooseTranslationAttempt1,
        Boolean chooseTranslationAttempt2,
        Boolean constructWordAttempt1,
        Boolean constructWordAttempt2,
        Boolean writeWordAttempt1,
        Boolean writeWordAttempt2,
        Boolean pronounceWordAttempt1,
        Boolean pronounceWordAttempt2,
        Boolean completed
) {}
