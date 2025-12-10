package org.example.vocabulary.mapper;


import io.swagger.v3.oas.annotations.media.Content;
import org.example.vocabulary.dto.WordRequestDto;
import org.example.vocabulary.dto.WordResponseDto;
import org.example.vocabulary.entity.Category;
import org.example.vocabulary.entity.Word;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WordMapper {



    @Mapping(target = "categoryId", source = "category.id")
    WordResponseDto toDto(Word word);

    List<WordResponseDto> toDto(List<Word> word);

    Word toEntity( WordResponseDto dto);
    List<Word> toEntity(List<WordResponseDto> dto);







}
