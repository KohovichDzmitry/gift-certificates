package ru.clevertec.ecl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CheckGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(EntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(IncorrectData.builder()
                        .message(exception.getMessage())
                        .code(HttpStatus.NOT_FOUND.value() + exception.getId())
                        .build());
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(EntityWithNameExistsException exception) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(IncorrectData.builder()
                        .message(exception.getMessage())
                        .code("400" + HttpStatus.NOT_ACCEPTABLE.value())
                        .build());
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(IncorrectData.builder()
                        .message(exception.getMessage())
                        .code("400" + HttpStatus.BAD_REQUEST.value())
                        .build());
    }
}
