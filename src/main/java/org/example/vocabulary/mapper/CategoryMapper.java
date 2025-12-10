package org.example.vocabulary.mapper;

import org.example.vocabulary.dto.response.CategoryResponseDto;
import org.example.vocabulary.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

 @Mapper(componentModel = "spring")
 public interface CategoryMapper {


     CategoryResponseDto toDto(Category category);

     List<CategoryResponseDto> toDto(List<Category> categories);

     @Mapping(target = "id", ignore = true)
     @Mapping(target = "deleteAt", ignore = true)
     Category toEntity(CategoryResponseDto dto);

     List<Category> toEntity(List<CategoryResponseDto> dto);


 }
