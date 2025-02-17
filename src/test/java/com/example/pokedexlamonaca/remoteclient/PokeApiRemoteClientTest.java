package com.example.pokedexlamonaca.remoteclient;

import com.example.pokedexlamonaca.model.exception.PokemonNotFoundException;
import com.example.pokedexlamonaca.model.exception.WrongPokemonDetailsUrlException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PokeApiRemoteClientTest {
    private final PokeApiRemoteClient pokeApiRemoteClient = new PokeApiRemoteClient();

    @Test
    public void testGetPokemonBasicInfo() {
        //Setup

        //Execute
        PokeApiRemoteClient.PokemonBasicInfo result = pokeApiRemoteClient.getPokemonBasicInfo("zubat");

        //Verify
        Assertions.assertNotNull(result);
        Assertions.assertEquals("41", result.getId());
        Assertions.assertNotNull(result.getSpecies());
        Assertions.assertEquals("zubat", result.getSpecies().getName());
    }

    @Test
    public void testGetPokemonBasicInfoNotFound() {
        //Setup

        //Execute

        //Verify
        Assertions.assertThrows(PokemonNotFoundException.class, () -> pokeApiRemoteClient.getPokemonBasicInfo("pippo"));
    }

    @Test
    public void testGetPokemonDetails() {
        //Setup

        //Execute
        PokeApiRemoteClient.PokemonDetails result = pokeApiRemoteClient.getPokemonDetails("https://pokeapi.co/api/v2/pokemon-species/41");

        //Verify
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getFlavourTextEntryList());
        Assertions.assertNotNull(result.getHabitat());
        Assertions.assertEquals("cave", result.getHabitat().getName());
        Assertions.assertFalse(result.getIsLegendary());
    }

    @Test
    public void testGetPokemonDetailsNotFound() {
        //Setup

        //Execute

        //Verify
        Assertions.assertThrows(WrongPokemonDetailsUrlException.class, () -> pokeApiRemoteClient.getPokemonDetails("https://pokeapi.co/api/v2/pokemon-species/pippo"));
    }

}
