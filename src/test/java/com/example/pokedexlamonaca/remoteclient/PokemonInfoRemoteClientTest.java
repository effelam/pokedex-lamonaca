package com.example.pokedexlamonaca.remoteclient;

import com.example.pokedexlamonaca.model.excpetion.PokemonNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PokemonInfoRemoteClientTest {
    private final PokemonInfoRemoteClient pokemonInfoRemoteClient = new PokemonInfoRemoteClient();

    @Test
    public void testGetPokemonInfo() {
        //Setup

        //Execute
        PokemonInfoRemoteClient.PokemonInfo result = pokemonInfoRemoteClient.getPokemonInfo("zubat");

        //Verify
        Assertions.assertNotNull(result);
        Assertions.assertEquals("41", result.getId());
        Assertions.assertNotNull(result.getSpecies());
        Assertions.assertEquals("zubat", result.getSpecies().getName());
    }

    @Test
    public void testGetPokemonInfoNotFound() {
        //Setup

        //Execute

        //Verify
        Assertions.assertThrows(PokemonNotFoundException.class, () -> pokemonInfoRemoteClient.getPokemonInfo("pippo"));
    }

    @Test
    public void testGetPokemonSpecies() {
        //Setup

        //Execute
        PokemonInfoRemoteClient.PokemonSpecies result = pokemonInfoRemoteClient.getPokemonSpecies("https://pokeapi.co/api/v2/pokemon-species/41");

        //Verify
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getFlavourTextEntryList());
        Assertions.assertNotNull(result.getHabitat());
        Assertions.assertEquals("cave", result.getHabitat().getName());
        Assertions.assertFalse(result.getIsLegendary());
    }

    @Test
    public void testGetPokemonSpeciesNotFound() {
        //Setup

        //Execute

        //Verify
        Assertions.assertThrows(PokemonNotFoundException.class, () -> pokemonInfoRemoteClient.getPokemonInfo("https://pokeapi.co/api/v2/pokemon-species/pippo"));
    }

}
