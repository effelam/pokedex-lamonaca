package com.example.pokedexlamonaca.model.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;

@Getter
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class WrongPokemonDetailsUrlException extends RestClientException {
    private final String pokemonDetailsUrl;
    public WrongPokemonDetailsUrlException(String pokemonDetailsUrl) {
        super("Wrong pokemon details url");
        this.pokemonDetailsUrl = pokemonDetailsUrl;
    }
}
