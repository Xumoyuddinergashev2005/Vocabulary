package org.example.vocabulary.service;

import lombok.RequiredArgsConstructor;
import org.example.vocabulary.dto.StudySessionCreateDto;
import org.example.vocabulary.dto.StudySessionResponseDto;
import org.example.vocabulary.dto.StudySessionWordResponseDto;
import org.example.vocabulary.entity.*;
import org.example.vocabulary.exception.NotFoundException;
import org.example.vocabulary.repository.CategoryRepository;
import org.example.vocabulary.repository.StudySessionRepository;
import org.example.vocabulary.repository.StudySessionWordRepository;
import org.example.vocabulary.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudySessionService {

    private final StudySessionRepository sessionRepository;
    private final CategoryRepository categoryRepository;
    private final WordRepository wordRepository;
    private final StudySessionWordRepository studySessionWordRepository;

    public StudySessionResponseDto startSession(StudySessionCreateDto dto, User user) {
        // 1. Category tekshiriladi
        Category category = categoryRepository.findByIdAndDeleteAtIsNull(dto.categoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));

        // 2. Session yaratiladi
        StudySession session = StudySession.builder()
                .user(user)
                .category(category)
                .startedAt(LocalDateTime.now())
                .completed(false)
                .build();

        sessionRepository.save(session);

        // 3. Category ichidagi Word larni sessionga qo‘shish
        List<Word> words = wordRepository.findAllByCategoryIdAndDeleteAtIsNull(category.getId());
        List<StudySessionWord> sessionWords = words.stream().map(word -> StudySessionWord.builder()
                .session(session)
                .word(word)
                .chooseWordAttempt1(false)
                .chooseWordAttempt2(false)
                .chooseTranslationAttempt1(false)
                .chooseTranslationAttempt2(false)
                .constructWordAttempt1(false)
                .constructWordAttempt2(false)
                .writeWordAttempt1(false)
                .writeWordAttempt2(false)
                .pronounceWordAttempt1(false)
                .pronounceWordAttempt2(false)
                .completed(false)
                .build()).toList();

        studySessionWordRepository.saveAll(sessionWords);

        // 4. DTO qaytarish
        return new StudySessionResponseDto(
                session.getId(),
                category.getId(),
                session.getStartedAt(),
                session.getCompleted()
        );
    }


    public List<StudySessionWordResponseDto> getSessionWords(Long sessionId, User user) {
        // 1. Session tekshirish
        StudySession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new NotFoundException("Session not found"));

        // 2. Session user bilan mosligini tekshirish
        if (!session.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized access to this session");
        }

        // 3. StudySessionWord larni olish
        List<StudySessionWord> words = studySessionWordRepository.findAllBySessionId(sessionId);

        // 4. DTO ga map qilish
        return words.stream().map(word -> new StudySessionWordResponseDto(
                word.getWord().getId(),
                word.getWord().getWordEn(),
                word.getWord().getWordUz(),
                word.getChooseWordAttempt1(),
                word.getChooseWordAttempt2(),
                word.getChooseTranslationAttempt1(),
                word.getChooseTranslationAttempt2(),
                word.getConstructWordAttempt1(),
                word.getConstructWordAttempt2(),
                word.getWriteWordAttempt1(),
                word.getWriteWordAttempt2(),
                word.getPronounceWordAttempt1(),
                word.getPronounceWordAttempt2(),
                word.getCompleted()
        )).toList();
    }




}

