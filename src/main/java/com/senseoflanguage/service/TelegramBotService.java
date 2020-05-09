package com.senseoflanguage.service;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface TelegramBotService {

    void onCommandReceived(Update update);

    void onMessageReceived(Update update);
}
