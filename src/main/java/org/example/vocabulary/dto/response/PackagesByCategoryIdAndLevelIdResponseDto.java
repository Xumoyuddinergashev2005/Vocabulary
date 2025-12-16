package org.example.vocabulary.dto.response;


import lombok.Builder;

@Builder
public record PackagesByCategoryIdAndLevelIdResponseDto(
        Long id,
        Long packNumber,
        Boolean completed
) {
}
