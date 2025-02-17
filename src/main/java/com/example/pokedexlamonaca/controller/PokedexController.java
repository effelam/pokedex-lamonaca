package com.example.pokedexlamonaca.controller;

import com.example.pokedexlamonaca.model.PokemonInfo;
import com.example.pokedexlamonaca.service.PokedexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pokemon")
public class PokedexController {

    @Autowired
    private PokedexService pokedexService;

    /**
     * Retrieves the basic information of a Pokémon based on its name.
     *
     * @param pokemonName the name of the Pokémon to retrieve information for
     * @return a ResponseEntity containing the information about the Pokémon
     */
    @GetMapping("/{pokemonName}")
    public ResponseEntity<PokemonInfo> getPokemonInfo(@PathVariable("pokemonName") String pokemonName) {
        return ResponseEntity.ok(pokedexService.getPokemonInfo(pokemonName));
    }

    /**
     * Retrieves translated information about a Pokémon based on its name.
     * The translation of the Pokémon's Pokedex entry is determined by its habitat and legendary status.
     *
     * @param pokemonName the name of the Pokémon to retrieve and translate information for
     * @return a ResponseEntity containing the translated information about the Pokémon
     */
    @GetMapping("/translated/{pokemonName}")
    public ResponseEntity<PokemonInfo> getPokemonInfoTranslated(@PathVariable("pokemonName") String pokemonName) {
        return ResponseEntity.ok(pokedexService.getPokemonInfoTranslated(pokemonName));
    }

}
