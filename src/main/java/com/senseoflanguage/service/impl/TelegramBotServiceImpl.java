package com.senseoflanguage.service.impl;

import com.senseoflanguage.service.TelegramBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Collections;

@Service
public class TelegramBotServiceImpl implements TelegramBotService {

    private final ReplyKeyboardMarkup replyKeyboardMarkup;

    @Autowired
    public TelegramBotServiceImpl(ReplyKeyboardMarkup replyKeyboardMarkup) {
        this.replyKeyboardMarkup = replyKeyboardMarkup;
    }

    @Override
    public SendMessage update(Update update) {
        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId(), "Hi!");
        sendMessage.setReplyMarkup(this.replyKeyboardMarkup);
        return sendMessage;
    }

}