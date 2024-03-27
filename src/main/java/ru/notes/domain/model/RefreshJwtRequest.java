package ru.notes.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Запрос нового access jwt")
public class RefreshJwtRequest {
    @Schema(description = "refresh token", example = "tregwedgwerd")
    public String refreshToken;
}
