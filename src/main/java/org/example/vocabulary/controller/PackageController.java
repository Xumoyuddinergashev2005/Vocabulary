package org.example.vocabulary.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.vocabulary.dto.LevelResponseDto;
import org.example.vocabulary.dto.request.PackageRequestDto;
import org.example.vocabulary.dto.request.PackagesByCategoryIdAndLevelIdRequestDto;
import org.example.vocabulary.dto.request.WordRequestDto;
import org.example.vocabulary.dto.response.MessageResponse;
import org.example.vocabulary.dto.response.PackageResponseDto;
import org.example.vocabulary.entity.User;
import org.example.vocabulary.service.PackageService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/package")
@RequiredArgsConstructor
@Tag(name = "Package")
public class PackageController {
    private final PackageService packageService;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid PackageRequestDto dto,
                                    @AuthenticationPrincipal User user){
        packageService.create(dto, user);
        return ResponseEntity.ok(MessageResponse.success("Package created successfully"));
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllPackages(){
        return ResponseEntity.ok(packageService.getAll());
    }


    @GetMapping("/by/pack{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(packageService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id,
                                        @AuthenticationPrincipal User user){
        packageService.deleteById(id, user);
        return ResponseEntity.ok(MessageResponse.success("Package deleted successfully"));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody PackageResponseDto dto){
        packageService.update(id, dto);
        return ResponseEntity.ok(MessageResponse.success("Package updated successfully"));
    }

    @GetMapping("/all/by/category/{categoryId}/level/{levelId}")
    public ResponseEntity<?> getAllPackagesByCategoryIdAndLevelId(
            @PathVariable Long categoryId,
            @PathVariable Long levelId) {
        return ResponseEntity.ok(packageService.getAllPackagesById(categoryId, levelId));
    }


}