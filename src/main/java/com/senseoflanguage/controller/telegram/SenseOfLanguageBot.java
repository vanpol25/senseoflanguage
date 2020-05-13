package com.senseoflanguage.controller.telegram;

import com.senseoflanguage.model.enums.CollectionType;
import com.senseoflanguage.model.enums.WordState;
import com.senseoflanguage.service.TelegramBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.xml.ws.RequestWrapper;

@Component
public class SenseOfLanguageBot {

    private final TelegramBotService telegramBotService;

    @Autowired
    public SenseOfLanguageBot(TelegramBotService telegramBotService) {
        this.telegramBotService = telegramBotService;
    }

    public void start(Update update) {
        telegramBotService.start(update);
    }

    public void chooseCollection(Update update) {
        telegramBotService.chooseCollection(update);
    }

    public void loadCollection(Update update, CollectionType collectionType) {
        telegramBotService.loadCollection(update, collectionType);
    }

    public void show(Update update) {
        telegramBotService.show(update);
    }

    public void chooseDifficult(Update update, WordState wordState) {
        telegramBotService.chooseDifficult(update, wordState);
    }

    public void showExamples(Update update) {
        telegramBotService.showExamples(update);
    }

    public void showDetails(Update update) {
        telegramBotService.showDetails(update);
    }

    public void showAllInfo(Update update) {
        telegramBotService.showAllInfo(update);
    }

    public void showStatistic(Update update) {
        telegramBotService.showStatistic(update);
    }

}
