package ru.notes.util;

import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.notes.domain.entity.Role;
import ru.notes.domain.model.JwtAuthentication;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRole(claims.get("role", Role.class));
        jwtInfoToken.setUsername(claims.get("username", String.class));
        jwtInfoToken.setEmail(claims.getSubject());
        return jwtInfoToken;
    }
}