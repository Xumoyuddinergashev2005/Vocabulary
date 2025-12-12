package org.example.vocabulary.mapper;


import org.example.vocabulary.dto.StudySessionResponseDto;
import org.example.vocabulary.dto.response.CategoryResponseDto;
import org.example.vocabulary.entity.Category;
import org.example.vocabulary.entity.StudySession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudySessionMapper {
  /*  CategoryResponseDto toDto(Category category);

    List<CategoryResponseDto> toDto(List<Category> categories);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleteAt", ignore = true)
    Category toEntity(CategoryResponseDto dto);

    List<Category> toEntity(List<CategoryResponseDto> dto);*/

    StudySessionResponseDto toDto(StudySession studySession);

    List<StudySessionResponseDto> toDto(List<StudySession> studySessions);



}
