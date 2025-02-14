package com.example.pokedexlamonaca.service;

import com.example.pokedexlamonaca.model.PokemonInfo;
import com.example.pokedexlamonaca.remoteclient.PokemonInfoRemoteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokedexService {

    @Autowired
    private PokemonInfoRemoteClient pokemonInfoRemoteClient;

    public PokemonInfo getPokemonInfo(String pokemonName) {
        PokemonInfoRemoteClient.PokemonInfo remoteInfo = pokemonInfoRemoteClient.getPokemonInfo(pokemonName);
        PokemonInfoRemoteClient.PokemonSpecies species = pokemonInfoRemoteClient.getPokemonSpecies(remoteInfo.getSpecies().getUrl());
        return PokemonInfo.builder()
                .name(remoteInfo.getSpecies().getName())
                .pokedexEntry(species.getFlavourTextEntryList().stream()
                        .filter(ft -> ft.getLanguage().getName().equals("en"))
                        .findFirst()
                        .map(PokemonInfoRemoteClient.FlavourTextEntry::getFlavourText)
                        .orElse("No pokedex entry available in english")
                )
                .habitat(species.getHabitat().getName())
                .isLegendary(species.getIsLegendary())
                .build();
    }

    public PokemonInfo getPokemonInfoTranslated(String pokemonName) {
        PokemonInfo info = getPokemonInfo(pokemonName);
        //TODO translation
        return info;
    }

}
