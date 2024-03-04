package ru.notes.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.notes.entity.User;
import ru.notes.model.UserBody;
import ru.notes.service.UserService;

@Tag(name = "Users", description = "Users API")
@RestController
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "user/create")
    public ResponseEntity<User> createUser(@RequestBody UserBody request) {
        log.info("REQUEST > {}", request );
        return ResponseEntity.ok().body(userService.createUser(request));
    }
}
