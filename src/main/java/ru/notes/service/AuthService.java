package ru.notes.service;

import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.notes.components.JwtProvider;
import ru.notes.domain.entity.Role;
import ru.notes.domain.entity.User;
import ru.notes.domain.model.JwtAuthentication;
import ru.notes.domain.model.JwtAuthenticationResponse;
import ru.notes.domain.model.SignInRequest;
import ru.notes.domain.model.SignUpRequest;
import ru.notes.exception.AuthException;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public JwtAuthenticationResponse signIn(@NonNull SignInRequest authRequest) {
        final User user = userService.getByEmail(authRequest.getEmail())
                .orElseThrow(() -> new AuthException("Пользователь не найден"));
        if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(user.getEmail(), refreshToken);
            return new JwtAuthenticationResponse(accessToken, refreshToken);
        }
        else {
            throw new AuthException("Неверный пароль");
        }
    }

    public JwtAuthenticationResponse signUp(@NonNull SignUpRequest authRequest) {
        final User user = User.builder()
                .username(authRequest.getUsername())
                .email(authRequest.getEmail())
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .role(Role.USER)
                .build();
        userService.create(user);
        final String accessToken = jwtProvider.generateAccessToken(user);
        final String refreshToken = jwtProvider.generateRefreshToken(user);
        refreshStorage.put(user.getEmail(), refreshToken);
        return new JwtAuthenticationResponse(accessToken, refreshToken);
    }

    public JwtAuthenticationResponse getAccessToken(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String email = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(email);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.getByEmail(email)
                        .orElseThrow(() -> new AuthException("Пользователь не найден"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                return new JwtAuthenticationResponse(accessToken, null);
            }
        }
        return new JwtAuthenticationResponse(null, null);
    }

    public JwtAuthenticationResponse refresh(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String email = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(email);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.getByEmail(email)
                        .orElseThrow(() -> new AuthException("Пользователь не найден"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(email, newRefreshToken);
                return new JwtAuthenticationResponse(accessToken, newRefreshToken);
            }
        }
        throw  new AuthException("Невалидный JWT refresh токен");
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
