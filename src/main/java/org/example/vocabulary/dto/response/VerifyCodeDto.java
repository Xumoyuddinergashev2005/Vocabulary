package org.example.vocabulary.dto.response;

import jakarta.validation.constraints.Email;
import lombok.Builder;

@Builder
public record VerifyCodeDto(
        @Email(message = "Invalid format")
        String email,
        //@Min(value = 4, message = "Code must be at least 4 characters long")
        String code,
        //@Min(value = 5, message = "Password must be at least 5 characters long")
        String newPassword
) {
}