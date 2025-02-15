package com.example.pokedexlamonaca.remoteclient;

import com.example.pokedexlamonaca.model.excpetion.PokemonNotFoundException;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class PokeApiRemoteClient {
    private final String BASE_URL = "https://pokeapi.co/api/v2/";
    private final RestClient restClient = RestClient.create();

    public PokemonBasicInfo getPokemonBasicInfo(String pokemonName) {
        return restClient
                .get()
                .uri(BASE_URL + "pokemon/{pokemonName}", pokemonName)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new PokemonNotFoundException(response.getStatusCode());
                })
                .body(PokemonBasicInfo.class);
    }

    public PokemonDetails getPokemonDetails(String pokemonDetailsUrl) {
        return restClient
                .get()
                .uri(pokemonDetailsUrl)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new PokemonNotFoundException(response.getStatusCode());
                })
                .body(PokemonDetails.class);
    }

    @Getter
    @Setter
    public static class PokemonBasicInfo {
        private String id;
        private RemoteObject info;
    }

    @Getter
    @Setter
    public static class PokemonDetails {
        @JsonProperty("flavor_text_entries")
        private List<FlavourTextEntry> flavourTextEntryList;
        private RemoteObject habitat;
        @JsonProperty("is_legendary")
        private Boolean isLegendary;
    }

    @Getter
    @Setter
    public static class FlavourTextEntry {
        @JsonProperty("flavor_text")
        private String flavourText;
        private RemoteObject language;
        private RemoteObject version;
    }

    @Getter
    @Setter
    public static class RemoteObject {
        private String name;
        private String url;
    }
}
