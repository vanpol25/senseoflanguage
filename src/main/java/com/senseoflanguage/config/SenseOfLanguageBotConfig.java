package com.senseoflanguage.config;

import com.senseoflanguage.controller.telegram.SenseOfLanguageBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Collections;

@Component
@Slf4j
public class SenseOfLanguageBotConfig extends TelegramLongPollingBot {

    @Autowired
    private SenseOfLanguageBot senseOfLanguageBot;

    @Value("${telegram.token}")
    private String token;

    @Value("${telegram.name}")
    private String name;

    @Bean
//    @Scope(value = "prototype")
    public ReplyKeyboardMarkup getReplyKeyboardMarkup() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("Easy");
        keyboardRow.add("Middle");
        keyboardRow.add("Hard");
        return new ReplyKeyboardMarkup()
                .setResizeKeyboard(true)
                .setKeyboard(Collections.singletonList(keyboardRow));
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = senseOfLanguageBot.onUpdateReceived(update);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.info(e.getMessage());
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
