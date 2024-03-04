package ru.notes.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.notes.entity.User;
import ru.notes.model.UserBody;
import ru.notes.repository.UsersRepository;

import java.util.UUID;

@Slf4j
@Service
public class UserService {
    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional
    public User createUser(UserBody body) {
        UUID uuid = UUID.randomUUID();
        User user = new User(uuid.toString(), body.username(), body.password(), body.email());
        return usersRepository.save(user);
    }
}
