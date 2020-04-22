package com.senseoflanguage.controller.telegram;

import com.senseoflanguage.service.TelegramBotService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class SenseOfLanguageBot {

    private TelegramBotService telegramBotService;

    public SendMessage onUpdateReceived(Update update) {
        SendMessage sendMessage = telegramBotService.update(update);
        return sendMessage;
    }

}
