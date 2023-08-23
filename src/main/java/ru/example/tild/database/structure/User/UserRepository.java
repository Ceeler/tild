package ru.example.tild.database.structure.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsById(Long id);

    Optional<User> findByEmail(String email);
}
