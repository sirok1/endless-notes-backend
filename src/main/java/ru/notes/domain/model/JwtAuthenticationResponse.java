package ru.notes.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Ответ c токеном доступа")
public class JwtAuthenticationResponse {
    @Schema(description = "Тип токенов", example = "Bearer")
    private final String type = "Bearer";

    @Schema(description = "Токен доступа", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYyMjUwNj...")
    private String accessToken;

    @Schema(description = "Токен обновления", example = "erwqfdwfe2wfwefwevwefrwef")
    private String refreshToken;
}