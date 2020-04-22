package com.senseoflanguage.config;

import com.senseoflanguage.controller.telegram.SenseOfLanguageBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.interfaces.Validable;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVideoNote;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class SenseOfLanguageBotConfig extends TelegramLongPollingBot {

    @Autowired
    private SenseOfLanguageBot senseOfLanguageBot;

    @Value("${telegram.token}")
    private String token;

    @Value("${telegram.name}")
    private String name;

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = senseOfLanguageBot.onUpdateReceived(update);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }

}
