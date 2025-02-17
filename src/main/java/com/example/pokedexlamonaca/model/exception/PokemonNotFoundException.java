package com.example.pokedexlamonaca.model.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;

@Getter
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PokemonNotFoundException extends RestClientException {
    private final String pokemonName;
    public PokemonNotFoundException(String pokemonName) {
        super("Pokemon not found");
        this.pokemonName = pokemonName;
    }
}
