package com.senseoflanguage.config;

import com.senseoflanguage.controller.telegram.SenseOfLanguageBot;
import com.senseoflanguage.model.enums.CollectionType;
import com.senseoflanguage.model.enums.TelegramCommand;
import com.senseoflanguage.model.enums.WordState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class SenseOfLanguageBotConfig extends TelegramLongPollingBot {

    @Value("${telegram.token}")
    private String token;

    @Value("${telegram.name}")
    private String name;

    @Autowired
    private SenseOfLanguageBot senseOfLanguageBot;

    private static final ExecutorService executor = Executors.newFixedThreadPool(100);

    @Override
    public void onUpdateReceived(Update update) {
        executor.submit(() -> {
            try {
                try {
                    commandReceived(update);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void commandReceived(Update update) {
        String commandText;
        TelegramCommand command;
        if (update.hasMessage()) {
            commandText = update.getMessage().getText();
        } else if (update.hasCallbackQuery()) {
            commandText = update.getCallbackQuery().getData();
        } else {
            return;
        }

        command = TelegramCommand.getCommand(commandText);

        switch (command) {
            case START: {
                senseOfLanguageBot.start(update);
                break;
            }
            case CHOOSE_COLLECTION: {
                senseOfLanguageBot.chooseCollection(update);
                break;
            }
            case SHOW_ANSWER: {
                senseOfLanguageBot.show(update);
                break;
            }
            case EASY: {
                senseOfLanguageBot.chooseDifficult(update, WordState.KNOW);
                break;
            }
            case MEDIUM: {
                senseOfLanguageBot.chooseDifficult(update, WordState.DO_NOT_FULL_KNOW);
                break;
            }
            case HARD: {
                senseOfLanguageBot.chooseDifficult(update, WordState.DO_NOT_KNOW);
                break;
            }
            case EXAMPLES: {
                senseOfLanguageBot.showExamples(update);
                break;
            }
            case DETAILS: {
                senseOfLanguageBot.showDetails(update);
                break;
            }
            case ALL_INFO: {
                senseOfLanguageBot.showAllInfo(update);
                break;
            }
            case STATISTIC: {
                senseOfLanguageBot.showStatistic(update);
                break;
            }
            case BASE1:
            case BASE2:
            case BASE3:
            case BASE4:
            case BASE5:
            case BASE6: {
                senseOfLanguageBot.loadCollection(update, CollectionType.valueOf(command.name()));
                break;
            }
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
