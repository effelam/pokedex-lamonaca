package com.example.pokedexlamonaca.factory;

import com.example.pokedexlamonaca.model.PokemonInfo;
import com.example.pokedexlamonaca.remoteclient.PokemonInfoRemoteClient;

public class PokemonInfoFactory {
    public static PokemonInfo createPokemonInfoFromRemote(PokemonInfoRemoteClient.PokemonInfo remoteInfo, PokemonInfoRemoteClient.PokemonSpecies species) {
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
}
