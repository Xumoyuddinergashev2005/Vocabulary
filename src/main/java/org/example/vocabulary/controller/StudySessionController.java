package org.example.vocabulary.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.vocabulary.dto.StudySessionCreateDto;
import org.example.vocabulary.dto.response.MessageResponse;
import org.example.vocabulary.entity.User;
import org.example.vocabulary.service.StudySessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
@Tag(name = "Study Session")
public class StudySessionController {

    private final StudySessionService sessionService;

    @PostMapping("/start")
    public ResponseEntity<?> startSession(
            @RequestBody @Valid StudySessionCreateDto dto,
            @AuthenticationPrincipal User user
    ) {

        sessionService.startSession(dto, user);
        return ResponseEntity.ok(MessageResponse.success("Session started successfully"));
    }

    @GetMapping("/{sessionId}/words")
    public ResponseEntity<?> getSessionWords(
            @PathVariable Long sessionId,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(sessionService.getSessionWords(sessionId, user));
    }

}

