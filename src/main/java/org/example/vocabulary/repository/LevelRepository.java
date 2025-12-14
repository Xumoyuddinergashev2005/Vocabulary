package org.example.vocabulary.repository;


import org.example.vocabulary.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {

    List<Level> findAllByDeleteAtIsNullOrderByCreatedAtDesc();

    Optional<Level> findByIdAndDeleteAtIsNull(Long id);
}
