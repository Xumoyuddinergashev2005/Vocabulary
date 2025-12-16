package org.example.vocabulary.dto;


import lombok.Builder;
import org.example.vocabulary.dto.response.WordResponseDto;

import java.util.List;

@Builder
public record PackWordResponseDto(
        Long packId,
        Long wordId
) {
}
