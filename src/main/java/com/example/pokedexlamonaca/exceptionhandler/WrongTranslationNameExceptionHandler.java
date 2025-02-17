package com.example.pokedexlamonaca.exceptionhandler;

import com.example.pokedexlamonaca.model.exception.WrongTranslationNameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class WrongTranslationNameExceptionHandler {
    @ExceptionHandler(WrongTranslationNameException.class)
    public ResponseEntity handleException(WrongTranslationNameException e) {
        log.error("Wrong translation name {}", e.getTranslationName());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

}
