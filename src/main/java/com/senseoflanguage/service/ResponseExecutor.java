package com.senseoflanguage.service;

import com.senseoflanguage.model.Word;
import com.senseoflanguage.model.enums.CollectionType;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface ResponseExecutor {
    void startPresentProfile(Update update);

    void startNewProfile(Update update);

    void chooseCollection(Update update);

    void collectionFinishAndStatistic(Update update, CollectionType collectionType);

    void loadWord(Update update, String wordId);

    void showByWordId(Update update, String currentWordId);

    void showExamples(Update update, Word word);

    void showDetails(Update update, Word word);

    void showAllInfo(Update update, Word word);

    void showStatistic(Update update, CollectionType collectionType);
}
