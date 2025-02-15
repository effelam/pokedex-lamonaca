package com.example.pokedexlamonaca.service;

import com.example.pokedexlamonaca.factory.PokemonInfoFactory;
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
        return PokemonInfoFactory.createPokemonInfoFromRemote(remoteInfo, species);
    }

    public PokemonInfo getPokemonInfoTranslated(String pokemonName) {
        PokemonInfo info = getPokemonInfo(pokemonName);
        //TODO translation
        return info;
    }

}
