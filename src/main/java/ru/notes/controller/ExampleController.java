package ru.notes.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.notes.service.UserService;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("example")
@RequiredArgsConstructor
@Tag(name = "Примеры", description = "Примеры запросов с разными правами доступа")
public class ExampleController {
    private final UserService service;

    @GetMapping
    @Operation(summary = "Доступен только авторизованным пользователям")
    public String example() {
        return "Hello, world!";
    }

}