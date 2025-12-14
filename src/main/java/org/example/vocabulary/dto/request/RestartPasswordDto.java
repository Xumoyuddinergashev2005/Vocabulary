package org.example.vocabulary.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Builder;

@Builder
public record RestartPasswordDto(
        @Email(message = "Invalid format")
        String email,
        String username
) { }