package ru.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.notes.entity.User;

public interface UsersRepository extends JpaRepository<User, String> {}
