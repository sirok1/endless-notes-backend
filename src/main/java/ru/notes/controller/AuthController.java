package ru.notes.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.notes.domain.model.*;
import ru.notes.service.AuthService;

import java.util.List;

@RestController
@RequestMapping("v1/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public ResponseEntity<JwtAuthenticationResponse> signUp(@RequestBody @Valid SignUpRequest request) {
        final JwtAuthenticationResponse token = authService.signUp(request);
        return ResponseEntity.ok(token);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("sign-in")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody @Valid SignInRequest request) {
        final JwtAuthenticationResponse token = authService.signIn(request);
        return ResponseEntity.ok(token);
    }

    @Operation(summary = "Получение новых токенов")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("refresh")
    private ResponseEntity<JwtAuthenticationResponse> getNewTokens(@RequestBody RefreshJwtRequest request) {
        final JwtAuthenticationResponse token = authService.refresh(request.getRefreshToken());
        return  ResponseEntity.ok(token);
    }
}
