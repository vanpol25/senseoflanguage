package com.senseoflanguage.service;

import com.senseoflanguage.model.Word;

import java.io.IOException;

public interface WordDefinition {

    Word getDefinition(String word) throws IOException;

}
