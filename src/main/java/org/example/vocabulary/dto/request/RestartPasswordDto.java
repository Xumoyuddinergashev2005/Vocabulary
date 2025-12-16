package org.example.vocabulary.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record RestartPasswordDto(
        @Email(message = "Invalid format")
        String email,
        @NotNull(message = "Cannot be null")
        String username
) { }