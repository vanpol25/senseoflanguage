package com.senseoflanguage.service;

import com.senseoflanguage.model.Word;

import java.util.List;

public interface WordService {

    Word create(Word word);

    Word update(Word word);

    Word delete(String id);

    Word delete(Word word);

    List<Word> createList(List<Word> words);

    List<Word> updateList(List<Word> words);

    List<Word> deleteList(List<Word> words);

    Word findById(String id);

}
