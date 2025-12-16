package org.example.vocabulary.repository;


import io.minio.messages.Owner;
import jakarta.validation.constraints.NotNull;
import org.example.vocabulary.entity.Category;
import org.example.vocabulary.entity.MyPackage;
import org.example.vocabulary.entity.Word;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PackageRepository extends JpaRepository<MyPackage, Long> {




    boolean existsByPackNumber(@NotNull(message = "Cannot be null") Long aLong);

    List<MyPackage> findAllByDeleteAtIsNullOrderByCreatedAtDesc();

    Optional<MyPackage> findByIdAndDeleteAtIsNull(Long id);

}
