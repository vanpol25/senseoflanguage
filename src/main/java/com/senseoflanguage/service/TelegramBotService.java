package com.senseoflanguage.service;

import com.senseoflanguage.model.enums.CollectionType;
import com.senseoflanguage.model.enums.WordState;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface TelegramBotService {

    void start(Update update);

    void chooseCollection(Update update);

    void loadCollection(Update update, CollectionType collectionType);

    void show(Update update);

    void chooseDifficult(Update update, WordState wordState);

    void showExamples(Update update);

    void showDetails(Update update);

    void showAllInfo(Update update);

    void showStatistic(Update update);

}
