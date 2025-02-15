package com.example.pokedexlamonaca.service;

import com.example.pokedexlamonaca.factory.PokemonInfoFactory;
import com.example.pokedexlamonaca.model.PokemonInfo;
import com.example.pokedexlamonaca.remoteclient.PokeApiRemoteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokedexService {

    @Autowired
    private PokeApiRemoteClient pokeApiRemoteClient;

    public PokemonInfo getPokemonInfo(String pokemonName) {
        PokeApiRemoteClient.PokemonBasicInfo remoteInfo = pokeApiRemoteClient.getPokemonBasicInfo(pokemonName);
        PokeApiRemoteClient.PokemonDetails species = pokeApiRemoteClient.getPokemonDetails(remoteInfo.getInfo().getUrl());
        return PokemonInfoFactory.createPokemonInfoFromRemote(remoteInfo, species);
    }

    public PokemonInfo getPokemonInfoTranslated(String pokemonName) {
        PokemonInfo info = getPokemonInfo(pokemonName);
        //TODO translation
        return info;
    }

}
