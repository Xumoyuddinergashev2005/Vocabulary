package org.example.vocabulary.service;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.vocabulary.dto.PackWordRequestDto;
import org.example.vocabulary.dto.PackWordResponseDto;
import org.example.vocabulary.dto.response.CategoryResponseDto;
import org.example.vocabulary.entity.*;
import org.example.vocabulary.exception.AlreadyExistException;
import org.example.vocabulary.exception.IllegalArgumentException;
import org.example.vocabulary.exception.NotFoundException;
import org.example.vocabulary.mapper.PackWordMapper;
import org.example.vocabulary.repository.PackWordRepository;
import org.example.vocabulary.repository.PackageRepository;
import org.example.vocabulary.repository.WordRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PackWordService {
    private final PackWordRepository packWordRepository;
    private final PackageRepository packageRepository;
    private final PackWordMapper packWordMapper;
    private final WordRepository wordRepository;

    public void create( PackWordRequestDto dto, User user) {
        if (packWordRepository.existsById(dto.packId()) ){
            throw new AlreadyExistException("Pack_id with this " + dto.packId() + " already exist");
        }

        PackWord packWord = PackWord.builder()
                .packId(dto.packId())
                .build();
        packWordRepository.save(packWord);

    }

    public List<PackWordResponseDto> getAll() {
        List<PackWord> all = packWordRepository.findAllByDeleteAtIsNullOrderByCreatedAtDesc();
        return packWordMapper.toDto(all);
    }

    public PackWordResponseDto getById(Long id) {
        PackWord packWord = packWordRepository.findByPackIdAndDeleteAtIsNull(id)
                .orElseThrow(() -> new NotFoundException("PackWord not found with:" + id));
        return packWordMapper.toDto(packWord);
    }

    public void deleteById(Long id, User user) {

        PackWord packWord = packWordRepository.findByPackIdAndDeleteAtIsNull(id)
                .orElseThrow(() -> new NotFoundException("PackWord not found"));

        packWord.setDeleteAt(LocalDateTime.now());
        packWordRepository.save(packWord);

    }

    public void update(Long id, PackWordResponseDto dto) {
        MyPackage myPackage = packageRepository.findByIdAndDeleteAtIsNull(id)
                .orElseThrow(() -> new NotFoundException("Package not found"));
        Word word = wordRepository.findByIdAndDeleteAtIsNull(id)
                .orElseThrow(() -> new NotFoundException("Word not found"));
        PackWord packWord =packWordRepository.findByPackIdAndDeleteAtIsNull(id)
                .orElseThrow(() -> new NotFoundException("PackWord not found"));





        Optional<PackWord> packWordOptional = packWordRepository.findByPackIdAndWordId(myPackage.getId(), word.getId());

        if (packWordOptional.isEmpty()) {
            throw new IllegalArgumentException("Word is not linked to the specified Package");
        }

        packWord.setWordId(dto.wordId());
        packWord.setPackId(dto.packId());
        packWordRepository.save(packWord);
    }
}
