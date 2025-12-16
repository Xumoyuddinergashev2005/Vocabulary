package org.example.vocabulary.repository;


import org.example.vocabulary.entity.PackWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PackWordRepository extends JpaRepository<PackWord, Long> {

    List<PackWord> findAllByDeleteAtIsNullOrderByCreatedAtDesc();

    Optional<PackWord> findByPackIdAndDeleteAtIsNull(Long id);

    Optional<PackWord> findByPackIdAndWordId(Long id, Long id1);

    Optional<PackWord> findByWordIdAndDeleteAtIsNull(Long id);
}
