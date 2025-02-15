package com.example.pokedexlamonaca.model.excpetion;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClientException;

@Getter
public class PokemonNotFoundException extends RestClientException {
    private final String message = "Pokemon not found";
    private final HttpStatusCode statusCode;

    public PokemonNotFoundException(HttpStatusCode statusCode){
        super(statusCode.toString());
        this.statusCode = statusCode;
    }
}
