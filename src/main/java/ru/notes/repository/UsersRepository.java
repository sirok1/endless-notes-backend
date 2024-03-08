package ru.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.notes.entity.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail (String email);
    boolean existsByEmail (String email);
}
