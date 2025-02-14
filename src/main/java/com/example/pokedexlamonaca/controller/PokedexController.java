package com.example.pokedexlamonaca.controller;

import com.example.pokedexlamonaca.model.PokemonInfo;
import com.example.pokedexlamonaca.service.PokedexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pokemon")
public class PokedexController {

    @Autowired
    private PokedexService pokedexService;

    @GetMapping("/{pokemonName}")
    public PokemonInfo getPokemonInfo(@PathVariable("pokemonName") String pokemonName) {
        return pokedexService.getPokemonInfo(pokemonName);
    }

    @GetMapping("/translated/{pokemonName}")
    public PokemonInfo getPokemonInfoTranslated(@PathVariable("pokemonName") String pokemonName) {
        return pokedexService.getPokemonInfoTranslated(pokemonName);
    }

}
