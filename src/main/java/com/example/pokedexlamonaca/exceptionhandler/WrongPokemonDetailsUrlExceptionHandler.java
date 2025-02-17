package com.example.pokedexlamonaca.exceptionhandler;

import com.example.pokedexlamonaca.model.exception.WrongPokemonDetailsUrlException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class WrongPokemonDetailsUrlExceptionHandler {
    @ExceptionHandler(WrongPokemonDetailsUrlException.class)
    public ResponseEntity handleException(WrongPokemonDetailsUrlException e) {
        log.error("Wrong Pokemon details url {}", e.getPokemonDetailsUrl());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

}
