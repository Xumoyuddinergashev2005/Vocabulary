package org.example.vocabulary.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.vocabulary.dto.response.CategoryResponseDto;
import org.example.vocabulary.dto.request.CategoryRequestDto;
import org.example.vocabulary.dto.response.MessageResponse;
import org.example.vocabulary.entity.Level;
import org.example.vocabulary.entity.User;
import org.example.vocabulary.service.CategoryService;
import org.example.vocabulary.service.LevelService;
import org.example.vocabulary.service.WordService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Tag(name = "Category")
public class CategoryController {


    private final CategoryService categoryService;
    private final WordService wordService;
    private final LevelService levelService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid CategoryRequestDto dto,
                                    @AuthenticationPrincipal User user){
        categoryService.create(dto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(MessageResponse.success("Category created successfully"));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id,
                                        @AuthenticationPrincipal User user){
        categoryService.deleteById(id, user);
        return ResponseEntity.ok(MessageResponse.success("Category deleted successfully"));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody CategoryResponseDto dto){
         categoryService.update(id, dto);
         return ResponseEntity.ok(MessageResponse.success("Category updated successfully"));
    }

    @GetMapping("/{id}/all/words")
    public ResponseEntity<?> categoryById(@PathVariable Long id) {
        return ResponseEntity.ok(wordService.getByCategoryId(id));


    }

    @GetMapping("/category/{categoryId}/levels")
    public ResponseEntity<?> getAllByCategoryId(@PathVariable Long categoryId){

        return ResponseEntity.ok( categoryService.getAllLevels(categoryId));

    }
}
