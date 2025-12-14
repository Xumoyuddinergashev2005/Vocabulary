package org.example.vocabulary.mapper;

import org.example.vocabulary.dto.LevelResponseDto;
import org.example.vocabulary.dto.response.WordResponseDto;
import org.example.vocabulary.entity.Level;
import org.example.vocabulary.entity.Word;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LevelMapper {


    LevelResponseDto toDto(Level level);

    List<LevelResponseDto> toDto(List<Level> level);

    Level toEntity(LevelResponseDto dto);

    List<Level> toEntity(List<LevelResponseDto> dto);
}
