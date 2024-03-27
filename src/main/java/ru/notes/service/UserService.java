package ru.notes.service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.notes.domain.entity.User;
import ru.notes.exception.RestException;
import ru.notes.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    @Transactional
    public User save(User user) {
        return repository.save(user);
    }


    public User create(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new RestException(409, "Пользователь с таким email уже существует");

        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new RestException(409, "Пользователь с таким email уже существует");
        }

        return save(user);
    }


    public Optional<User> getByEmail(@Email String email) {
        return repository.findByEmail(email);
    }
}