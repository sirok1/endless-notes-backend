package ru.notes.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.notes.model.ErrorResponse;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RestException.class)
    public ResponseEntity<ErrorResponse> handleRestException(RestException ex){
        log.error("rest exception!");
        ErrorResponse errorResponse = ErrorResponse.builder()
                .detail(ex.getErrorMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(ex.getStatusCode()));
    }
}
