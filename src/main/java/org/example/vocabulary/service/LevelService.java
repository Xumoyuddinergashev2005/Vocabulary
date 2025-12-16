package org.example.vocabulary.service;


import lombok.RequiredArgsConstructor;
import org.example.vocabulary.dto.request.LevelRequestDto;
import org.example.vocabulary.dto.LevelResponseDto;
import org.example.vocabulary.entity.Category;
import org.example.vocabulary.entity.Level;
import org.example.vocabulary.entity.User;
import org.example.vocabulary.exception.AlreadyExistException;
import org.example.vocabulary.exception.NotFoundException;
import org.example.vocabulary.mapper.LevelMapper;
import org.example.vocabulary.repository.CategoryRepository;
import org.example.vocabulary.repository.LevelRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LevelService {
    private final LevelRepository levelRepository;
    private final LevelMapper levelMapper;
    private final CategoryRepository categoryRepository;

    public void create(LevelRequestDto dto, User user) {

        if (levelRepository.existsByName(dto.name())) {
            throw new AlreadyExistException("Level with name " + dto.name() + " already exist in this category");
        }

        Category category = categoryRepository.findByIdAndDeleteAtIsNull(dto.categoryId()).orElseThrow(() -> new NotFoundException("This category is not exist"));
        Level level = Level.builder()
                .name(dto.name())
                .category(category)
                .build();
        levelRepository.save(level);

    }

    public List<LevelResponseDto> getAll() {
        List<Level> all = levelRepository.findAllByDeleteAtIsNullOrderByCreatedAtDesc();
        return levelMapper.toDto(all);
    }

    public LevelResponseDto getById(Long id) {
        Level level = levelRepository.findByIdAndDeleteAtIsNull(id)
                .orElseThrow(() -> new NotFoundException("Level not found with:" + id));
        return levelMapper.toDto(level);
    }

    public void deleteById(Long id, User user) {

        Level level = levelRepository.findByIdAndDeleteAtIsNull(id)
                .orElseThrow(() -> new NotFoundException("Level not found"));

        level.setDeleteAt(LocalDateTime.now());
        levelRepository.save(level);

    }

    public void update(Long id, LevelResponseDto dto) {
        Level level = levelRepository.findByIdAndDeleteAtIsNull(id)
                .orElseThrow(() -> new NotFoundException("Level not found"));

        Category category = categoryRepository.findByIdAndDeleteAtIsNull(dto.categoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));

        level.setName(dto.name());
        level.setCategory(category);
        levelRepository.save(level);
    }

}
