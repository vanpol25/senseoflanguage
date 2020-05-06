package com.senseoflanguage.service.impl;

import com.senseoflanguage.service.TelegramBotService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

public class TelegramBotServiceImpl implements TelegramBotService {

    @Override
    public SendMessage update(Update update) {
        return null;
    }

}
