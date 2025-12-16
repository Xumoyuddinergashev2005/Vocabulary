package org.example.vocabulary.mapper;

import org.example.vocabulary.dto.LevelResponseByCategoryIdDto;
import org.example.vocabulary.dto.response.PackagesByCategoryIdAndLevelIdResponseDto;
import org.example.vocabulary.entity.Level;
import org.example.vocabulary.entity.MyPackage;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface PackagesByCategoryIdAndLevelIdMapper {
    PackagesByCategoryIdAndLevelIdResponseDto toDto(MyPackage myPackage);

    List<PackagesByCategoryIdAndLevelIdResponseDto> toDto(List<MyPackage> myPackages);
}
