package com.example.pokedexlamonaca.remoteclient;

import com.example.pokedexlamonaca.model.exception.WrongTranslationNameException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

@Service
public class FunTranslatorRemoteClient {
    private final String BASE_URL = "https://api.funtranslations.com/translate/";
    private final RestClient restClient = RestClient.create();
    public FunTranslation translateTest(String text, String language) {
        return restClient
                .post()
                .uri(BASE_URL + language + ".json")
                .body(buildMap(text))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new WrongTranslationNameException(language);
                })
                .body(FunTranslation.class);
    }

    private MultiValueMap<String, String> buildMap(String text) {
        MultiValueMap<String, String> result = new HttpHeaders();
        result.add("text", text);
        return result;
    }

    @Getter
    @Setter
    public static class FunTranslation {
        private Content contents;
    }

    @AllArgsConstructor
    private static class TextToTranslate {
        private String text;
    }

    @Getter
    @Setter
    public static class Content {
        private String translated;
        private String text;
        private String translation;
    }

}
