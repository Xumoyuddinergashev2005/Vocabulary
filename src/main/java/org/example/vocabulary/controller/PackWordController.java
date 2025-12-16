package org.example.vocabulary.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.vocabulary.dto.PackWordRequestDto;
import org.example.vocabulary.dto.PackWordResponseDto;
import org.example.vocabulary.dto.request.CategoryRequestDto;
import org.example.vocabulary.dto.response.CategoryResponseDto;
import org.example.vocabulary.dto.response.MessageResponse;
import org.example.vocabulary.entity.User;
import org.example.vocabulary.service.PackWordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pack_word")
@RequiredArgsConstructor
@Tag(name = "PackWord")
public class PackWordController {
    private final PackWordService packWordService;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid PackWordRequestDto dto,
                                    @AuthenticationPrincipal User user){
        packWordService.create(dto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(MessageResponse.success("pack connected with wors successfully"));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPackWords(){
        return ResponseEntity.ok(packWordService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(packWordService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id,
                                        @AuthenticationPrincipal User user){
        packWordService.deleteById(id, user);
        return ResponseEntity.ok(MessageResponse.success("PackWord deleted successfully"));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody PackWordResponseDto dto){
        packWordService.update(id, dto);
        return ResponseEntity.ok(MessageResponse.success("PackWord updated successfully"));
    }


}
