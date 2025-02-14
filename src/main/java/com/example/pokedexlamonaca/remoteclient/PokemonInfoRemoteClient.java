package com.example.pokedexlamonaca.remoteclient;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonInfoRemoteClient {
    public PokemonInfo getPokemonInfo(String pokemonName) {
        return null;
    }

    public PokemonSpecies getPokemonSpecies(String pokemonId) {
        return null;
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
