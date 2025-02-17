package com.example.pokedexlamonaca.factory;

import com.example.pokedexlamonaca.model.PokemonInfo;
import com.example.pokedexlamonaca.remoteclient.PokeApiRemoteClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class PokemonInfoFactoryTest {

    @Test
    public void testCreatePokemonInfo() {
        //Setup
        PokeApiRemoteClient.PokemonBasicInfo pokemonBasicInfo = buildPokemonBasicInfo();
        PokeApiRemoteClient.PokemonDetails pokemonDetails = buildPokemonDetails("en");

        //Execute
        PokemonInfo result = PokemonInfoFactory.createPokemonInfoFromRemote(pokemonBasicInfo, pokemonDetails);

        //Verify
        Assertions.assertEquals("pokemonName", result.getName());
        Assertions.assertEquals("flavourText", result.getPokedexEntry());
        Assertions.assertEquals("habitatName", result.getHabitat());
        Assertions.assertTrue(result.getIsLegendary());
    }

    @Test
    public void testCreatePokemonInfoNoEngFlavourText() {
        //Setup
        PokeApiRemoteClient.PokemonBasicInfo pokemonBasicInfo = buildPokemonBasicInfo();
        PokeApiRemoteClient.PokemonDetails pokemonDetails = buildPokemonDetails("it");

        //Execute
        PokemonInfo result = PokemonInfoFactory.createPokemonInfoFromRemote(pokemonBasicInfo, pokemonDetails);

        //Verify
        Assertions.assertEquals("pokemonName", result.getName());
        Assertions.assertEquals("No pokedex entry available in english", result.getPokedexEntry());
        Assertions.assertEquals("habitatName", result.getHabitat());
        Assertions.assertTrue(result.getIsLegendary());
    }

    private PokeApiRemoteClient.PokemonBasicInfo buildPokemonBasicInfo() {
        PokeApiRemoteClient.PokemonBasicInfo pokemonBasicInfo = new PokeApiRemoteClient.PokemonBasicInfo();
        pokemonBasicInfo.setId("pokemonId");
        PokeApiRemoteClient.RemoteObject species = new PokeApiRemoteClient.RemoteObject();
        species.setName("pokemonName");
        species.setUrl("pokemonUrl");
        pokemonBasicInfo.setSpecies(species);
        return pokemonBasicInfo;
    }

    private PokeApiRemoteClient.PokemonDetails buildPokemonDetails(String languageName) {
        PokeApiRemoteClient.PokemonDetails pokemonDetails = new PokeApiRemoteClient.PokemonDetails();
        PokeApiRemoteClient.FlavourTextEntry flavourTextEntry = new PokeApiRemoteClient.FlavourTextEntry();
        flavourTextEntry.setFlavourText("flavourText");
        PokeApiRemoteClient.RemoteObject language = new PokeApiRemoteClient.RemoteObject();
        language.setName(languageName);
        language.setUrl("languageUrl");
        flavourTextEntry.setLanguage(language);
        pokemonDetails.setFlavourTextEntryList(Collections.singletonList(flavourTextEntry));
        PokeApiRemoteClient.RemoteObject habitat = new PokeApiRemoteClient.RemoteObject();
        habitat.setName("habitatName");
        pokemonDetails.setHabitat(habitat);
        pokemonDetails.setIsLegendary(true);
        return pokemonDetails;
    }
}
