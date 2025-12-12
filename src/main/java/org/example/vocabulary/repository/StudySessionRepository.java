package org.example.vocabulary.repository;

import org.example.vocabulary.entity.StudySession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudySessionRepository extends JpaRepository<StudySession, Long> {

    Optional<StudySession> findByIdAndCompletedFalse(Long id);

    List<StudySession> findAllByUserIdAndCompletedFalse(Long userId);
}

