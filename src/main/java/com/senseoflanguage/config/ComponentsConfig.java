package com.senseoflanguage.config;

import com.senseoflanguage.model.enums.CollectionType;
import com.senseoflanguage.model.enums.TelegramCommand;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class ComponentsConfig {

    @Bean
    @Qualifier("menu")
    public InlineKeyboardMarkup getMenu() {
        List<InlineKeyboardButton> inlineKeyboardButtons = Arrays.asList(
                new InlineKeyboardButton(TelegramCommand.EXAMPLES.text())
                        .setCallbackData(TelegramCommand.EASY.command()),
                new InlineKeyboardButton(TelegramCommand.DETAILS.text())
                        .setCallbackData(TelegramCommand.DETAILS.command())
        );

        return new InlineKeyboardMarkup(
                Collections.singletonList(inlineKeyboardButtons));
    }

    @Bean
    @Qualifier("isNoLast")
    public ReplyKeyboardMarkup getIsNoLast() {
        KeyboardRow keyboard = new KeyboardRow();
        keyboard.add(new KeyboardButton().setText(TelegramCommand.IS_NO_LAST.text()));
        return new ReplyKeyboardMarkup(Collections.singletonList(keyboard));
    }

    @Bean
    @Qualifier("chooseAnswer")
    public ReplyKeyboardMarkup getChooseAnswer() {
        KeyboardRow keyboard = new KeyboardRow();
        keyboard.add(new KeyboardButton().setText(TelegramCommand.EASY.text()));
        keyboard.add(new KeyboardButton().setText(TelegramCommand.MEDIUM.text()));
        keyboard.add(new KeyboardButton().setText(TelegramCommand.HARD.text()));

        return new ReplyKeyboardMarkup(Collections.singletonList(keyboard));
    }

    @Bean
    @Qualifier("wordInfo")
    public InlineKeyboardMarkup getWordInfo() {
        List<InlineKeyboardButton> inlineKeyboardButtons = Arrays.asList(
                new InlineKeyboardButton(TelegramCommand.EXAMPLES.text())
                        .setCallbackData(TelegramCommand.EASY.command()),
                new InlineKeyboardButton(TelegramCommand.DETAILS.text())
                        .setCallbackData(TelegramCommand.DETAILS.command()),
                new InlineKeyboardButton(TelegramCommand.ALL_INFO.text())
                        .setCallbackData(TelegramCommand.ALL_INFO.command())
        );

        return new InlineKeyboardMarkup(
                Collections.singletonList(inlineKeyboardButtons));
    }

    @Bean
    @Qualifier("chooseCollection")
    public InlineKeyboardMarkup getChooseCollection() {
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        if (keyboard.getKeyboard() == null) {
            keyboard.setKeyboard(new ArrayList<>());
        }

        for (CollectionType collectionType : CollectionType.values()) {
            keyboard.getKeyboard()
                    .add(Collections.singletonList(
                            new InlineKeyboardButton()
                                    .setText(collectionType.name())
                                    .setCallbackData("/" + collectionType.name())));
        }
        return keyboard;
    }

}
