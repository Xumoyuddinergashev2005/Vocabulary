package org.example.vocabulary.repository;

import org.example.vocabulary.entity.Category;
import org.example.vocabulary.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WordRepository extends JpaRepository<Word, Long> {


    List<Word> findAllByDeleteAtIsNullOrderByCreatedAtDesc();
    Optional<Word> findByIdAndDeleteAtIsNull(Long id);

    List<Word> findAllByCategoryAndDeleteAtIsNullOrderByCreatedAtDesc(Category category);



}
