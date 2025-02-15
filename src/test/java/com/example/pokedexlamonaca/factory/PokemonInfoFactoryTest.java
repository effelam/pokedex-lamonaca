package com.example.pokedexlamonaca.factory;

import com.example.pokedexlamonaca.model.PokemonInfo;
import com.example.pokedexlamonaca.remoteclient.PokemonInfoRemoteClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class PokemonInfoFactoryTest {

    @Test
    public void testCreatePokemonInfo() {
        //Setup
        PokemonInfoRemoteClient.PokemonInfo pokemonInfo = buildPokemonInfo();
        PokemonInfoRemoteClient.PokemonSpecies pokemonSpecies = buildPokemonSpecies("en");

        //Execute
        PokemonInfo result = PokemonInfoFactory.createPokemonInfoFromRemote(pokemonInfo, pokemonSpecies);

        //Verify
        Assertions.assertEquals("pokemonName", result.getName());
        Assertions.assertEquals("flavourText", result.getPokedexEntry());
        Assertions.assertEquals("habitatName", result.getHabitat());
        Assertions.assertTrue(result.getIsLegendary());
    }

    @Test
    public void testCreatePokemonInfoNoEngFlavourText() {
        //Setup
        PokemonInfoRemoteClient.PokemonInfo pokemonInfo = buildPokemonInfo();
        PokemonInfoRemoteClient.PokemonSpecies pokemonSpecies = buildPokemonSpecies("it");

        //Execute
        PokemonInfo result = PokemonInfoFactory.createPokemonInfoFromRemote(pokemonInfo, pokemonSpecies);

        //Verify
        Assertions.assertEquals("pokemonName", result.getName());
        Assertions.assertEquals("No pokedex entry available in english", result.getPokedexEntry());
        Assertions.assertEquals("habitatName", result.getHabitat());
        Assertions.assertTrue(result.getIsLegendary());
    }

    private PokemonInfoRemoteClient.PokemonInfo buildPokemonInfo() {
        PokemonInfoRemoteClient.PokemonInfo pokemonInfo = new PokemonInfoRemoteClient.PokemonInfo();
        pokemonInfo.setId("pokemonId");
        PokemonInfoRemoteClient.Species species = new PokemonInfoRemoteClient.Species();
        species.setName("pokemonName");
        species.setUrl("pokemonUrl");
        pokemonInfo.setSpecies(species);
        return pokemonInfo;
    }

    private PokemonInfoRemoteClient.PokemonSpecies buildPokemonSpecies(String languageName) {
        PokemonInfoRemoteClient.PokemonSpecies pokemonSpecies = new PokemonInfoRemoteClient.PokemonSpecies();
        PokemonInfoRemoteClient.FlavourTextEntry flavourTextEntry = new PokemonInfoRemoteClient.FlavourTextEntry();
        flavourTextEntry.setFlavourText("flavourText");
        PokemonInfoRemoteClient.Language language = new PokemonInfoRemoteClient.Language();
        language.setName(languageName);
        language.setUrl("languageUrl");
        flavourTextEntry.setLanguage(language);
        pokemonSpecies.setFlavourTextEntryList(Collections.singletonList(flavourTextEntry));
        PokemonInfoRemoteClient.Habitat habitat = new PokemonInfoRemoteClient.Habitat();
        habitat.setName("habitatName");
        pokemonSpecies.setHabitat(habitat);
        pokemonSpecies.setIsLegendary(true);
        return pokemonSpecies;
    }
}
