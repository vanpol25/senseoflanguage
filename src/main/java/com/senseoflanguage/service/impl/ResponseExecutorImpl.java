package com.senseoflanguage.service.impl;

import com.senseoflanguage.config.SenseOfLanguageBotConfig;
import com.senseoflanguage.model.Collection;
import com.senseoflanguage.model.Word;
import com.senseoflanguage.model.enums.CollectionType;
import com.senseoflanguage.service.ResponseExecutor;
import com.senseoflanguage.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class ResponseExecutorImpl implements ResponseExecutor {

    String htmlForm = "<b>bold</b>, <strong>bold</strong>\n" +
            "<i>italic</i>, <em>italic</em>\n" +
            "<u>underline</u>, <ins>underline</ins>\n" +
            "<s>strikethrough</s>, <strike>strikethrough</strike>, <del>strikethrough</del>\n" +
            "<b>bold <i>italic bold <s>italic bold strikethrough</s> <u>underline italic bold</u></i> bold</b>\n" +
            "<a href=\"http://www.example.com/\">inline URL</a>\n" +
            "<a href=\"tg://user?id=123456789\">inline mention of a user</a>\n" +
            "<code>inline fixed-width code</code>\n" +
            "<pre>pre-formatted fixed-width code block</pre>\n" +
            "<pre><code class=\"language-python\">pre-formatted fixed-width code block written in the Python programming language</code></pre>";

    private final SenseOfLanguageBotConfig solbc;
    private final WordService wordService;
    private final ReplyKeyboardMarkup start;
    private final ReplyKeyboardMarkup loadWord;
    private final ReplyKeyboardMarkup showAnswer;
    private final InlineKeyboardMarkup chooseCollection;

    @Autowired
    public ResponseExecutorImpl(SenseOfLanguageBotConfig senseOfLanguageBotConfig,
                                WordService wordService,
                                @Qualifier(value = "start") ReplyKeyboardMarkup start,
                                @Qualifier(value = "loadWord") ReplyKeyboardMarkup loadWord,
                                @Qualifier(value = "showAnswer") ReplyKeyboardMarkup showAnswer,
                                @Qualifier(value = "chooseCollection") InlineKeyboardMarkup chooseCollection) {
        this.solbc = senseOfLanguageBotConfig;
        this.wordService = wordService;
        this.start = start;
        this.loadWord = loadWord;
        this.showAnswer = showAnswer;
        this.chooseCollection = chooseCollection;
    }

    @Override
    public void startPresentProfile(Update update) {
        String chatId = getChatId(update);

        SendMessage sendMessage = new SendMessage()
                .setChatId(chatId)
                .setText("Hola!")
                .setReplyMarkup(start);

        execute(sendMessage);
    }

    @Override
    public void startNewProfile(Update update) {
        String chatId = getChatId(update);

        SendMessage startMessage = new SendMessage()
                .setChatId(chatId)
                .setText("Hola!")
                .setReplyMarkup(start);
        execute(startMessage);

        startMessage
                .setText("Hi! What's up! Lets start...\n" +
                        "Here will be some motivation text, but later.\n" +
                        "I will explain you what you can do,\n" +
                        "what you will know due to this bot etc." +
                        "This text only to take place for testing and just to be.\n" +
                        "Thanks, choose button 'Choose collection' below");
        execute(startMessage);
        startMessage
                .setText("This collection will be attached to your profile.\n" +
                        "You will manage your statistic about every word," +
                        "from your current collection.\n" +
                        "You can switch to another collections in any time," +
                        "than return to previous and all will be saved");
        execute(startMessage);
    }

    @Override
    public void chooseCollection(Update update) {
        String chatId = getChatId(update);

        SendMessage startMessage = new SendMessage()
                .setChatId(chatId)
                .setText("Choose collection you want:")
                .setReplyMarkup(chooseCollection);
        execute(startMessage);
    }

    @Override
    public void loadWord(Update update, String wordId) {
        String chatId = getChatId(update);

        Word word = wordService.findById(wordId);

        SendMessage startMessage = new SendMessage()
                .setChatId(chatId)
                .setText("Word: " + word.getEng())
                .setReplyMarkup(loadWord);
        execute(startMessage);
    }

    @Override
    public void showByWordId(Update update, String currentWordId) {
        String chatId = getChatId(update);

        Word word = wordService.findById(currentWordId);

        SendMessage startMessage = new SendMessage()
                .setChatId(chatId)
                .setText("Answer: " + word.getUkr())
                .setReplyMarkup(showAnswer);
        execute(startMessage);
    }

    @Override
    public void collectionFinishAndStatistic(Update update, CollectionType collectionType) {

    }

    @Override
    public void showExamples(Update update, Word word) {

    }

    @Override
    public void showDetails(Update update, Word word) {

    }

    @Override
    public void showAllInfo(Update update, Word word) {

    }

    @Override
    public void showStatistic(Update update, Collection collection) {

    }

    private String getChatId(Update update) {
        String chatId;
        if (update.getMessage() != null) {
            chatId = update.getMessage().getChatId().toString();
        } else {
            chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        }
        return chatId;
    }

    private void execute(SendMessage sendMessage) {
        try {
            solbc.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
