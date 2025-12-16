package org.example.vocabulary.service;


import lombok.RequiredArgsConstructor;
import org.example.vocabulary.dto.LevelResponseByCategoryIdDto;
import org.example.vocabulary.dto.LevelResponseDto;
import org.example.vocabulary.dto.response.CategoryResponseDto;
import org.example.vocabulary.dto.request.CategoryRequestDto;
import org.example.vocabulary.entity.Category;
import org.example.vocabulary.entity.Level;
import org.example.vocabulary.entity.User;
import org.example.vocabulary.exception.AlreadyExistException;
import org.example.vocabulary.exception.NotFoundException;
import org.example.vocabulary.mapper.CategoryMapper;
import org.example.vocabulary.mapper.LevelByCategoryIdMapper;
import org.example.vocabulary.repository.CategoryRepository;
import org.example.vocabulary.repository.LevelRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final LevelRepository levelRepository;
    private final LevelByCategoryIdMapper levelByCategoryIdMapper;


    public void create(CategoryRequestDto dto, User user) {

        if (categoryRepository.existsByName(dto.name()) ){
            throw new AlreadyExistException("Category with categoryName " + dto.name() + " already exist");
        }

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

    public List<LevelResponseByCategoryIdDto> getAllLevels(Long id) {
        Category category = categoryRepository.findByIdAndDeleteAtIsNull(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));
        List<Level> all = levelRepository.findAllByDeleteAtIsNullOrderByCreatedAtDesc();
        return levelByCategoryIdMapper.toDto(all);
    }
}




