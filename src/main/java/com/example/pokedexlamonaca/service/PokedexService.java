package com.example.pokedexlamonaca.service;

import com.example.pokedexlamonaca.factory.PokemonInfoFactory;
import com.example.pokedexlamonaca.helper.FindTranslationNameHelper;
import com.example.pokedexlamonaca.model.PokemonInfo;
import com.example.pokedexlamonaca.remoteclient.FunTranslatorRemoteClient;
import com.example.pokedexlamonaca.remoteclient.PokeApiRemoteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokedexService {

    @Autowired
    private PokeApiRemoteClient pokeApiRemoteClient;
    @Autowired
    private FunTranslatorRemoteClient funTranslatorRemoteClient;

    public PokemonInfo getPokemonInfo(String pokemonName) {
        PokeApiRemoteClient.PokemonBasicInfo pokemonBasicInfo = pokeApiRemoteClient.getPokemonBasicInfo(pokemonName);
        PokeApiRemoteClient.PokemonDetails pokemonDetails = pokeApiRemoteClient.getPokemonDetails(pokemonBasicInfo.getSpecies().getUrl());
        return PokemonInfoFactory.createPokemonInfoFromRemote(pokemonBasicInfo, pokemonDetails);
    }

    public PokemonInfo getPokemonInfoTranslated(String pokemonName) {
        PokemonInfo info = getPokemonInfo(pokemonName);
        String translationName = FindTranslationNameHelper.getTranslationName(info.getHabitat(), info.getIsLegendary());
        FunTranslatorRemoteClient.FunTranslation translation = funTranslatorRemoteClient.translateTest(info.getPokedexEntry(), translationName);
        info.setPokedexEntry(translation.getContents().getTranslated());
        return info;
    }

}
