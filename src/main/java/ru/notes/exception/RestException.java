package ru.notes.exception;

import lombok.Getter;

@Getter
public class RestException extends RuntimeException {
    private final int statusCode;
    private final String errorMessage;

    public RestException(String errorMessage) {
        this(500, errorMessage);
    }

    public RestException(int statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

}
