package org.example.vocabulary.service;


import lombok.RequiredArgsConstructor;
import org.example.vocabulary.dto.request.WordRequestDto;
import org.example.vocabulary.dto.response.WordResponseDto;
import org.example.vocabulary.entity.Category;
import org.example.vocabulary.entity.User;
import org.example.vocabulary.entity.Word;
import org.example.vocabulary.exception.NotFoundException;
import org.example.vocabulary.mapper.WordMapper;
import org.example.vocabulary.repository.CategoryRepository;
import org.example.vocabulary.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WordService {
    private final WordRepository wordRepository;
    private final CategoryRepository categoryRepository;
    private final WordMapper wordMapper;

    public void createWord(WordRequestDto dto, User user) {
        Category category = categoryRepository.findByIdAndDeleteAtIsNull(dto.categoryId()).orElseThrow(() -> new NotFoundException("This category is not exist"));

        Word word = Word.builder()
                .wordEn(dto.wordEn())
                .wordUz(dto.wordUz())
                .exampleSentence(dto.exampleSentence())
                .audioFile(dto.audioFile())
                .category(category)
                .build();

        wordRepository.save(word);

    }

    public List<WordResponseDto> getAll() {
        List<Word> all = wordRepository.findAllByDeleteAtIsNullOrderByCreatedAtDesc();
        return wordMapper.toDto(all);
    }

    public WordResponseDto getById(Long id) {
        Word word = wordRepository.findByIdAndDeleteAtIsNull(id).orElseThrow(() -> new NotFoundException("Word not found"));
        return wordMapper.toDto(word);
    }

    public void deleteById(Long wordId) {
        Word word = wordRepository.findByIdAndDeleteAtIsNull(wordId)
                .orElseThrow(() -> new NotFoundException("Word not found or already deleted"));
        word.setDeleteAt(LocalDateTime.now());
        wordRepository.save(word);
    }

    public void update(Long id, WordResponseDto dto) {
        Word word = wordRepository.findByIdAndDeleteAtIsNull(id)
                .orElseThrow(() -> new NotFoundException("Word not found"));

        Category category = categoryRepository.findByIdAndDeleteAtIsNull(dto.categoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));

        word.setWordEn(dto.wordEn());
        word.setWordUz(dto.wordUz());
        word.setExampleSentence(dto.exampleSentence());
        word.setAudioFile(dto.audioFile());
        word.setCategory(category);

        wordRepository.save(word);

    }

    public List<WordResponseDto> getByCategoryId(Long id) {

      Category category=categoryRepository.findByIdAndDeleteAtIsNull(id).orElseThrow(()-> new NotFoundException("Not found this category"));
        List<Word> all = wordRepository.findAllByCategoryAndDeleteAtIsNullOrderByCreatedAtDesc(category);
        return wordMapper.toDto(all);



    }
}




