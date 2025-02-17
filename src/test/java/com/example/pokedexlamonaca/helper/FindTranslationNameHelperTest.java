package com.example.pokedexlamonaca.helper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FindTranslationNameHelperTest {

    @Test
    public void testIsLegendary() {
        //Setup

        //Execute
        String result = FindTranslationNameHelper.getTranslationName("habitatName", true);

        //Verify
        Assertions.assertEquals("yoda", result);
    }

    @Test
    public void testCave() {
        //Setup

        //Execute
        String result = FindTranslationNameHelper.getTranslationName("cave", false);

        //Verify
        Assertions.assertEquals("yoda", result);
    }

    @Test
    public void testIsNotLegendaryNotCave() {
        //Setup

        //Execute
        String result = FindTranslationNameHelper.getTranslationName("sea", false);

        //Verify
        Assertions.assertEquals("shakespeare", result);
    }

}
