package com.senseoflanguage.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.senseoflanguage.exception.ModelNotFoundException;
import com.senseoflanguage.mapper.WordMapper;
import com.senseoflanguage.model.Word;
import com.senseoflanguage.model.words_api.WordWordsApi;
import com.senseoflanguage.service.WordDefinition;
import com.senseoflanguage.util.constants.ExceptionMessagesConst;
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
    public Word addDefinition(Word word) throws IOException {
        if (word == null) {
            throw new ModelNotFoundException(ExceptionMessagesConst.MODEL_IS_NULL_BEFORE_WORDS_API_SEARCH);
        } else if (word.getEng() == null) {
            throw new ModelNotFoundException(ExceptionMessagesConst.MODEL_FIELD_ENG_IS_NULL_BEFORE_WORDS_API_SEARCH);
        }

        String json = callWordsApi(word.getEng());
        WordWordsApi wordWordsApi = jsonToObj(json);

        return wordMapper.map(wordWordsApi, word);
    }

    private String callWordsApi(String word) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(WordsApiConst.URL + word)
                .get()
                .addHeader(WordsApiConst.HOST_WORDS_API_CONSTANT, host)
                .addHeader(WordsApiConst.KEY_WORDS_API_CONSTANT, key)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private WordWordsApi jsonToObj(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(json, WordWordsApi.class);
    }

}
