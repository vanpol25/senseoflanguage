package com.senseoflanguage.controller.telegram;

import com.senseoflanguage.service.TelegramBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class SenseOfLanguageBot {

    private final TelegramBotService telegramBotService;

    @Autowired
    public SenseOfLanguageBot(TelegramBotService telegramBotService) {
        this.telegramBotService = telegramBotService;
    }

    public SendMessage onUpdateReceived(Update update) {
        return telegramBotService.update(update);
    }

}
