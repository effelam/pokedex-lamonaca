package com.example.pokedexlamonaca.helper;

import java.util.Map;
import java.util.Objects;

public class FindTranslationNameHelper {
    private static final Map<String, String> habitatToTranslationNameMap = createMap();

    private static Map<String, String> createMap() {
        return Map.of(
                "cave", "yoda"
        );
    }

    public static String getTranslationName(String habitat, Boolean isLegendary) {
        String translationName = null;
        if (Boolean.TRUE.equals(isLegendary)) {
            translationName = "yoda";
        } else {
            translationName = habitatToTranslationNameMap.get(habitat);
        }
        return Objects.requireNonNullElse(translationName, "shakespeare");
    }
}
