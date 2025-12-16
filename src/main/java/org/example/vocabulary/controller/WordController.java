package org.example.vocabulary.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.vocabulary.dto.request.WordAddRequestDto;
import org.example.vocabulary.dto.request.WordRequestDto;
import org.example.vocabulary.dto.response.WordResponseDto;
import org.example.vocabulary.dto.response.MessageResponse;
import org.example.vocabulary.entity.User;
import org.example.vocabulary.service.WordService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/word")
@Tag(name="Word")
@RequiredArgsConstructor
public class WordController {
    private final WordService wordService;

    @PostMapping("/create")
    public ResponseEntity<?> createWord(@RequestBody @Valid WordRequestDto dto,
                                        @AuthenticationPrincipal User user){
        wordService.createWord(dto,user);
        return ResponseEntity.ok(MessageResponse.success("Word created successfully"));



    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllWords(){
        return ResponseEntity.ok(wordService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(wordService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id,
                                        @AuthenticationPrincipal User user){
        wordService.deleteById(id);
        return ResponseEntity.ok(MessageResponse.success("word deleted successfully"));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody WordResponseDto dto){
        wordService.update(id, dto);
        return ResponseEntity.ok(MessageResponse.success("word updated successfully"));
    }

    @PostMapping("add/package{id}")
    public ResponseEntity<?> add(@RequestBody WordAddRequestDto dto,
                                 @AuthenticationPrincipal User user){
        wordService.addWord(dto,user);
        return ResponseEntity.ok(MessageResponse.success("Word added to package successfully"));
    }




    }

