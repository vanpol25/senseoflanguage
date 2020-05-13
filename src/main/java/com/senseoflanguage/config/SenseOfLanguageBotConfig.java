package com.senseoflanguage.config;

import com.senseoflanguage.controller.telegram.SenseOfLanguageBot;
import com.senseoflanguage.exception.HtmlToImageException;
import com.senseoflanguage.model.enums.CollectionType;
import com.senseoflanguage.model.enums.TelegramCommand;
import com.senseoflanguage.model.enums.WordState;
import com.senseoflanguage.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
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
    @Autowired
    private ImageService imageService;

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

    private void commandReceived(Update update) throws IOException {
        String commandText;
        TelegramCommand command;
        if (update.hasMessage()) {
            if (update.getMessage().getText().length() > 20) {
                String html = update.getMessage().getText();
                exeT(update, "HtmlImageGeneratorImpl");
                long start = System.currentTimeMillis();
                InputFile inputFile = imageService.htmlToImage(html);
                exe(update, inputFile);
                long end = System.currentTimeMillis();
                exeT(update, Long.toString(end - start));
                return;
            }
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

    /*Temporary*/
    private void exe(Update update, InputFile inputFile) {
        SendPhoto photo = new SendPhoto();
        photo.setChatId(update.getMessage().getChatId());
        photo.setPhoto(inputFile);
        try {
            execute(photo);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void exeT(Update update, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText(text);
        try {
            execute(message);
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
