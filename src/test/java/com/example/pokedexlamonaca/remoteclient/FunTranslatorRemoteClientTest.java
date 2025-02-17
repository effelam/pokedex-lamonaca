package com.example.pokedexlamonaca.remoteclient;

import com.example.pokedexlamonaca.model.exception.WrongTranslationNameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FunTranslatorRemoteClientTest {
    private final FunTranslatorRemoteClient funTranslatorRemoteClient = new FunTranslatorRemoteClient();

    @Test
    public void testYodaTranslation() {
        //Setup
        String text = "I want to translate this sentence";

        //Execute
        FunTranslatorRemoteClient.FunTranslation result = funTranslatorRemoteClient.translateTest(text, "yoda");

        //Verify
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getContents());
        Assertions.assertNotNull(result.getContents().getText());
        Assertions.assertNotNull(result.getContents().getTranslated());
        Assertions.assertEquals("yoda", result.getContents().getTranslation());
    }

    @Test
    public void testTranslationUnknownLanguage() {
        //Setup
        String text = "I want to translate this sentence";

        //Execute

        //Verify
        Assertions.assertThrows(WrongTranslationNameException.class, () -> funTranslatorRemoteClient.translateTest(text, "ciao"));
    }

}
