package com.example.pokedexlamonaca.exceptionhandler;

import com.example.pokedexlamonaca.model.exception.PokemonNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class PokemonNotFoundExceptionHandler {
    @ExceptionHandler(PokemonNotFoundException.class)
    public ResponseEntity handleException(PokemonNotFoundException e) {
        log.error("Pokemon {} not found", e.getPokemonName());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

}
