package com.example.pokedexlamonaca.model.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;

@Getter
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class WrongTranslationNameException extends RestClientException {
    private final String translationName;
    public WrongTranslationNameException(String translationName) {
        super("Pokemon not found");
        this.translationName = translationName;
    }
}
