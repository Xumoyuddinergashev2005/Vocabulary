package org.example.vocabulary.mapper;


import org.example.vocabulary.dto.response.WordResponseDto;
import org.example.vocabulary.entity.Word;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WordMapper {



    @Mapping(target = "categoryId", source = "category.id")
    WordResponseDto toDto(Word word);

    List<WordResponseDto> toDto(List<Word> word);

    Word toEntity( WordResponseDto dto);
    List<Word> toEntity(List<WordResponseDto> dto);







}
