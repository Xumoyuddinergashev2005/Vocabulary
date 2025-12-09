package org.example.vocabulary.repository;


import org.example.vocabulary.entity.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends ListCrudRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByToken(String token);

    Optional<User> findByEmail(String email);
}