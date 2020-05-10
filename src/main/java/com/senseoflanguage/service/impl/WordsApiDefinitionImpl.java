package com.senseoflanguage.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.senseoflanguage.mapper.WordMapper;
import com.senseoflanguage.model.Word;
import com.senseoflanguage.model.words_api.WordWordsApi;
import com.senseoflanguage.service.WordDefinition;
import com.senseoflanguage.util.constants.WordsApiConst;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Qualifier(value = "wordsApi")
public class WordsApiDefinitionImpl implements WordDefinition {

    @Value(value = "${wordsapi.host}")
    private String host;
    @Value(value = "${wordsapi.key}")
    private String key;

    private final WordMapper wordMapper;

    @Autowired
    public WordsApiDefinitionImpl(WordMapper wordMapper) {
        this.wordMapper = wordMapper;
    }

    @Override
    public Word addDefinition(Word word) {
        if (word.getEng() == null) {
            return word;
        }

        String json = callWordsApi(word.getEng().split(" ")[0]);
        if (json == null) {
            return word;
        }
        WordWordsApi wordWordsApi = jsonToObj(json);

        return wordMapper.map(wordWordsApi, word);
    }

    private String callWordsApi(String word) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(WordsApiConst.URL + word)
                .get()
                .addHeader(WordsApiConst.HOST_WORDS_API_CONSTANT, host)
                .addHeader(WordsApiConst.KEY_WORDS_API_CONSTANT, key)
                .build();

        Response response;
        String json;
        try {
            response = client.newCall(request).execute();
            json = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        if (response.code() != 200) {
            System.out.println("In words api definition error. Status code=" + response.code() + ". Word=" + word);
            return null;
        }

        return json;
    }

    private WordWordsApi jsonToObj(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        WordWordsApi wordWordsApi;
        try {
            wordWordsApi = objectMapper.readValue(json, WordWordsApi.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
        return wordWordsApi;
    }

}
