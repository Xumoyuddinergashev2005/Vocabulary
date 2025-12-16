package org.example.vocabulary.dto.request;


import lombok.Builder;

@Builder
public record WordAddRequestDto(
        Long wordId,
        Long packId
) {
}
