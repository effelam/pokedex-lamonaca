package com.example.pokedexlamonaca.factory;

import com.example.pokedexlamonaca.model.PokemonInfo;
import com.example.pokedexlamonaca.remoteclient.PokeApiRemoteClient;

public class PokemonInfoFactory {
    public static PokemonInfo createPokemonInfoFromRemote(PokeApiRemoteClient.PokemonBasicInfo remoteInfo, PokeApiRemoteClient.PokemonDetails species) {
        return PokemonInfo.builder()
                .name(remoteInfo.getSpecies().getName())
                .pokedexEntry(species.getFlavourTextEntryList().stream()
                        .filter(ft -> ft.getLanguage().getName().equals("en"))
                        .findFirst()
                        .map(flavourTextEntry -> flavourTextEntry
                                .getFlavourText()
                                .replace("\n", " ")
                                .replace("\f", " ")
                        )
                        .orElse("No pokedex entry available in english")
                )
                .habitat(species.getHabitat().getName())
                .isLegendary(species.getIsLegendary())
                .build();
    }
}
