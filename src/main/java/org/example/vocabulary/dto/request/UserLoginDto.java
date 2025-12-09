package org.example.vocabulary.dto.request;

import lombok.Builder;

@Builder
public record UserLoginDto(
        String email,
        String password
) {
}