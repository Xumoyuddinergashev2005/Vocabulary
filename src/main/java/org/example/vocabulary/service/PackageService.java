package org.example.vocabulary.service;


import lombok.RequiredArgsConstructor;
import org.example.vocabulary.dto.LevelResponseDto;
import org.example.vocabulary.dto.request.PackageRequestDto;
import org.example.vocabulary.dto.request.PackagesByCategoryIdAndLevelIdRequestDto;
import org.example.vocabulary.dto.response.PackageResponseDto;
import org.example.vocabulary.dto.response.PackagesByCategoryIdAndLevelIdResponseDto;
import org.example.vocabulary.entity.Category;
import org.example.vocabulary.entity.Level;
import org.example.vocabulary.entity.MyPackage;
import org.example.vocabulary.entity.User;
import org.example.vocabulary.exception.AlreadyExistException;
import org.example.vocabulary.exception.IllegalArgumentException;
import org.example.vocabulary.exception.NotFoundException;
import org.example.vocabulary.mapper.PackageMapper;
import org.example.vocabulary.mapper.PackagesByCategoryIdAndLevelIdMapper;
import org.example.vocabulary.repository.CategoryRepository;
import org.example.vocabulary.repository.LevelRepository;

import org.example.vocabulary.repository.PackageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PackageService {
    private final PackageRepository packageRepository;
    private final PackageMapper packageMapper;
    private final CategoryRepository categoryRepository;
    private final LevelRepository levelRepository;
    private final PackagesByCategoryIdAndLevelIdMapper packagesByCategoryIdAndLevelIdMapper;

    public void create(PackageRequestDto dto, User user) {

        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));

        Level level = levelRepository.findById(dto.levelId())
                .orElseThrow(() -> new NotFoundException("Level not found"));


        if (!level.getCategory().getId().equals(category.getId())) {
            throw new IllegalArgumentException("Level does not belong to category");
        }

        if (packageRepository.existsByPackNumber(dto.packNumber())){
            throw new AlreadyExistException("Package already exists" + dto.packNumber() + " already exist");
        }

        MyPackage myPackage  = MyPackage.builder()
                .packNumber(dto.packNumber())
                .level(level)
                .category(category)
                .build();

       packageRepository.save(myPackage);

    }

    public List<PackageResponseDto> getAll() {
        List<MyPackage> all = packageRepository.findAllByDeleteAtIsNullOrderByCreatedAtDesc();
        return packageMapper.toDto(all);
    }

    public PackageResponseDto getById(Long id) {
        MyPackage myPackage = packageRepository.findByIdAndDeleteAtIsNull(id)
                .orElseThrow(() -> new NotFoundException("Package not found with:" + id));
        return packageMapper.toDto(myPackage);
    }

    public void deleteById(Long id, User user) {

        MyPackage myPackage = packageRepository.findByIdAndDeleteAtIsNull(id)
                .orElseThrow(() -> new NotFoundException("Package not found"));

        myPackage.setDeleteAt(LocalDateTime.now());
        packageRepository.save(myPackage);

    }

    public void update(Long id, PackageResponseDto dto) {
        MyPackage myPackage = packageRepository.findByIdAndDeleteAtIsNull(id)
                .orElseThrow(() -> new NotFoundException("Package not found"));

        Category category = categoryRepository.findByIdAndDeleteAtIsNull(dto.categoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));

        Level level = levelRepository.findByIdAndDeleteAtIsNull(dto.levelId())
                .orElseThrow(() -> new NotFoundException("Level not found"));

        if (!level.getCategory().getId().equals(category.getId())) {
            throw new IllegalArgumentException("Level does not belong to category");
        }

        myPackage.setPackNumber(dto.packNumber());
        myPackage.setCategory(category);
        myPackage.setLevel(level);
        packageRepository.save(myPackage);
    }


    public List<PackagesByCategoryIdAndLevelIdResponseDto> getAllPackagesById(Long categoryId, Long levelId){
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found"));

        Level level = levelRepository.findById(levelId)
                .orElseThrow(() -> new NotFoundException("Level not found"));


        if (!level.getCategory().getId().equals(category.getId())) {
            throw new IllegalArgumentException("Level does not belong to category");
        }

        List<MyPackage> all = packageRepository.findAllByDeleteAtIsNullOrderByCreatedAtDesc();

        return packagesByCategoryIdAndLevelIdMapper.toDto(all);


    }
}


