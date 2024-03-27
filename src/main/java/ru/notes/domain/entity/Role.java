package ru.notes.domain.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {
    ADMIN("ADMIN"),
    USER("USER");

    public final String value;

    @Override
    public String getAuthority() {
        return value;
    }
}