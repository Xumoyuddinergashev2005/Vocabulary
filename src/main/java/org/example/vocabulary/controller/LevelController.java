package org.example.vocabulary.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.vocabulary.dto.LevelRequestDto;
import org.example.vocabulary.dto.LevelResponseDto;
import org.example.vocabulary.dto.request.CategoryRequestDto;
import org.example.vocabulary.dto.response.CategoryResponseDto;
import org.example.vocabulary.dto.response.MessageResponse;
import org.example.vocabulary.entity.User;
import org.example.vocabulary.service.LevelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/level")
@RequiredArgsConstructor
@Tag(name = "Level")
public class LevelController {
    private final LevelService levelService;
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid LevelRequestDto dto,
                                    @AuthenticationPrincipal User user){
        levelService.create(dto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(MessageResponse.success("Level created successfully"));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllLevels(){
        return ResponseEntity.ok(levelService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(levelService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id,
                                        @AuthenticationPrincipal User user){
        levelService.deleteById(id, user);
        return ResponseEntity.ok(MessageResponse.success("Level deleted successfully"));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody LevelResponseDto dto){
        levelService.update(id, dto);
        return ResponseEntity.ok(MessageResponse.success("Level updated successfully"));
    }
}
