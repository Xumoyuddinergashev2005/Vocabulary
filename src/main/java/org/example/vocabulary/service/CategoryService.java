package org.example.vocabulary.service;


import lombok.RequiredArgsConstructor;
import org.example.vocabulary.dto.response.CategoryResponseDto;
import org.example.vocabulary.dto.request.CategoryRequestDto;
import org.example.vocabulary.entity.Category;
import org.example.vocabulary.entity.User;
import org.example.vocabulary.exception.NotFoundException;
import org.example.vocabulary.mapper.CategoryMapper;
import org.example.vocabulary.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    public void create(CategoryRequestDto dto, User user) {

        Category category = Category.builder()
                .description(dto.description())
                .name(dto.name())
                .build();
        categoryRepository.save(category);

    }

    public List<CategoryResponseDto> getAll() {
        List<Category> all = categoryRepository.findAllByDeleteAtIsNullOrderByCreatedAtDesc();
        return categoryMapper.toDto(all);
    }

    public CategoryResponseDto getById(Long id) {
        Category category = categoryRepository.findByIdAndDeleteAtIsNull(id)
                .orElseThrow(() -> new NotFoundException("Category not found with:" + id));
        return categoryMapper.toDto(category);
    }

    public void deleteById(Long id, User user) {

        Category category = categoryRepository.findByIdAndDeleteAtIsNull(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));

        category.setDeleteAt(LocalDateTime.now());
        categoryRepository.save(category);

    }

    public void update(Long id, CategoryResponseDto dto) {
        Category category = categoryRepository.findByIdAndDeleteAtIsNull(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));

        category.setName(dto.name());
        category.setDescription(dto.description());
        categoryRepository.save(category);
    }
}




