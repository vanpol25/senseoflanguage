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

import java.util.*;

@Configuration
public class ComponentsConfig {

    @Bean
    @Qualifier("start")
    public ReplyKeyboardMarkup start() {
        KeyboardRow keyboard = new KeyboardRow();
        keyboard.add(new KeyboardButton()
                .setText(TelegramCommand.CHOOSE_COLLECTION.text()));
        return new ReplyKeyboardMarkup(Collections.singletonList(keyboard))
                .setResizeKeyboard(true);
    }

    @Bean
    @Qualifier("chooseCollection")
    public InlineKeyboardMarkup chooseCollection() {
        List<List<InlineKeyboardButton>> inlineKeyboardButtons = new ArrayList<>();

        for (CollectionType collectionType : CollectionType.values()) {
            inlineKeyboardButtons.add(
                    Collections.singletonList(new InlineKeyboardButton()
                            .setText(collectionType.name())
                            .setCallbackData(collectionType.text())
                    ));
        }

        return new InlineKeyboardMarkup(inlineKeyboardButtons);
    }

    @Bean
    @Qualifier("loadWord")
    public ReplyKeyboardMarkup loadWord() {
        KeyboardRow keyboard = new KeyboardRow();
        keyboard.add(new KeyboardButton()
                .setText(TelegramCommand.SHOW_ANSWER.text()));
        return new ReplyKeyboardMarkup(Collections.singletonList(keyboard))
                .setResizeKeyboard(true);
    }

    @Bean
    @Qualifier("showAnswer")
    public ReplyKeyboardMarkup showAnswer() {
        KeyboardRow keyboard = new KeyboardRow();
        keyboard.add(new KeyboardButton().setText(TelegramCommand.EASY.text()));
//<--- Hidden until will be finded an algorithm --->
//        keyboard.add(new KeyboardButton().setText(TelegramCommand.MEDIUM.text()));
        keyboard.add(new KeyboardButton().setText(TelegramCommand.HARD.text()));

        return new ReplyKeyboardMarkup(Collections.singletonList(keyboard))
                .setResizeKeyboard(true);
    }

    /**
     * Bean for random values off course
     */
    @Bean
    @Qualifier("random")
    public Random getRandom() {
        return new Random();
    }


    ///////////////////////////////////////////////////////

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

}
