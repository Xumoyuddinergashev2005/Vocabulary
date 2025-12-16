package org.example.vocabulary.mapper;


import org.example.vocabulary.dto.LevelResponseByCategoryIdDto;
import org.example.vocabulary.dto.LevelResponseDto;
import org.example.vocabulary.entity.Level;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LevelByCategoryIdMapper {
    LevelResponseByCategoryIdDto toDto(Level level);

    List<LevelResponseByCategoryIdDto> toDto(List<Level> level);

}
