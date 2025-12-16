package org.example.vocabulary.mapper;

import org.example.vocabulary.dto.response.PackageResponseDto;
import org.example.vocabulary.entity.MyPackage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")
public interface PackageMapper {

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "levelId", source = "level.id")
    PackageResponseDto toDto(MyPackage myPackageEntity);

    List<PackageResponseDto> toDto(List<MyPackage> myPackages);

    MyPackage toEntity(PackageResponseDto dto);

    List<MyPackage> toEntity(List<PackageResponseDto> dto);
}
