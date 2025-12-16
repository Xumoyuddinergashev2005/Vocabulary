package org.example.vocabulary.mapper;


import org.example.vocabulary.dto.LevelResponseDto;
import org.example.vocabulary.dto.PackWordResponseDto;
import org.example.vocabulary.entity.Level;
import org.example.vocabulary.entity.PackWord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PackWordMapper {


    PackWordResponseDto toDto(PackWord packWord);

    List<PackWordResponseDto> toDto(List<PackWord> packWords);

    PackWord toEntity(PackWordResponseDto dto);

    List<PackWordResponseDto> toEntity(List<PackWordResponseDto> dto);
}
