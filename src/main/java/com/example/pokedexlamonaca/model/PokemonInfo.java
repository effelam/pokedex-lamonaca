package com.example.pokedexlamonaca.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PokemonInfo {
    private String name;
    private String pokedexEntry;
    private String habitat;
    private Boolean isLegendary;
}
