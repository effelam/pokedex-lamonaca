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
public class PokemonInfoRemoteClient {
    private final String BASE_URL = "https://pokeapi.co/api/v2/";
    private final RestClient restClient = RestClient.create();

    public PokemonInfo getPokemonInfo(String pokemonName) {
        return restClient
                .get()
                .uri(BASE_URL + "pokemon/{pokemonName}", pokemonName)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new PokemonNotFoundException(response.getStatusCode());
                })
                .body(PokemonInfo.class);
    }

    public PokemonSpecies getPokemonSpecies(String pokemonSpeciesUrl) {
        return restClient
                .get()
                .uri(pokemonSpeciesUrl)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new PokemonNotFoundException(response.getStatusCode());
                })
                .body(PokemonSpecies.class);
    }

    @Getter
    @Setter
    public static class PokemonInfo {
        private String id;
        private Species species;
    }

    @Getter
    @Setter
    public static class Species {
        private String name;
        private String url;
    }

    @Getter
    @Setter
    public static class PokemonSpecies {
        @JsonProperty("flavor_text_entries")
        private List<FlavourTextEntry> flavourTextEntryList;
        private Habitat habitat;
        @JsonProperty("is_legendary")
        private Boolean isLegendary;
    }

    @Getter
    @Setter
    public static class FlavourTextEntry {
        @JsonProperty("flavor_text")
        private String flavourText;
        private Language language;
        private Version version;
    }

    @Getter
    @Setter
    public static class Language {
        private String name;
        private String url;
    }

    @Getter
    @Setter
    public static class Version {
        private String name;
        private String url;
    }

    @Getter
    @Setter
    public static class Habitat {
        private String name;
        private String url;
    }
}
