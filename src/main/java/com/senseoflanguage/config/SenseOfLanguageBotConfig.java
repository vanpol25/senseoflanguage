package com.senseoflanguage.config;

import com.senseoflanguage.controller.telegram.SenseOfLanguageBot;
import com.senseoflanguage.model.enums.TelegramCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;

import java.io.Serializable;
import java.lang.reflect.Method;

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
        if (update.hasCallbackQuery() && TelegramCommand.hasCommand(update.getCallbackQuery().getData())) {
            senseOfLanguageBot.onCommandReceived(update);
        } else if (update.hasMessage() && TelegramCommand.hasText(update.getMessage().getText())) {
            senseOfLanguageBot.onMessageReceived(update);
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
