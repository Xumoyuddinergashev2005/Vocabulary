package org.example.vocabulary.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record StudySessionCreateDto(
        @NotNull(message = "Id is not be null")
        Long categoryId
) {}

