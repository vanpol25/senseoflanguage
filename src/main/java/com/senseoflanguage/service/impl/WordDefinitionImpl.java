package com.senseoflanguage.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.senseoflanguage.model.Word;
import com.senseoflanguage.service.WordDefinition;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WordDefinitionImpl implements WordDefinition {

    @Override
    public Word getDefinition(String englishWord) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://wordsapiv1.p.rapidapi.com/words/hatchback/typeOf")
                .get()
                .addHeader("x-rapidapi-host", "wordsapiv1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "cbaa791e2amsh123e771187206aep112b9cjsn30b2adb1626e")
                .build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();

        return jsonToObj(json);
    }

    private Word jsonToObj(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(json, Word.class);
    }

}
