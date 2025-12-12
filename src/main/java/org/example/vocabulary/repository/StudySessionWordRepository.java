package org.example.vocabulary.repository;


import org.example.vocabulary.entity.StudySessionWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudySessionWordRepository extends JpaRepository<StudySessionWord, Long> {

    List<StudySessionWord> findAllBySessionId(Long sessionId);

}

