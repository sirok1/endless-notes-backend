package ru.notes.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.notes.entity.User;
import ru.notes.model.UserRequestBody;
import ru.notes.repository.UsersRepository;

import java.util.UUID;

@Slf4j
@Service
public class UserService {
    private PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional
    public User createUser(UserRequestBody body) {
        UUID uuid = UUID.randomUUID();
        User user = new User(uuid.toString(), body.username(), passwordEncoder.encode(body.password()), body.email());
        return usersRepository.save(user);
    }
}
