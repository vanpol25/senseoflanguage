package com.senseoflanguage.service.impl;

import com.senseoflanguage.config.SenseOfLanguageBotConfig;
import com.senseoflanguage.exception.ModelNotFoundException;
import com.senseoflanguage.model.Collection;
import com.senseoflanguage.model.Profile;
import com.senseoflanguage.model.Word;
import com.senseoflanguage.model.WordInfo;
import com.senseoflanguage.model.enums.CollectionType;
import com.senseoflanguage.model.enums.WordState;
import com.senseoflanguage.service.ImageService;
import com.senseoflanguage.service.ProfileService;
import com.senseoflanguage.service.ResponseExecutor;
import com.senseoflanguage.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponseExecutorImpl implements ResponseExecutor {

    private final SenseOfLanguageBotConfig solbc;
    private final WordService wordService;
    private final ProfileService profileService;
    private final ImageService imageService;
    private final ReplyKeyboardMarkup start;
    private final ReplyKeyboardMarkup loadWord;
    private final ReplyKeyboardMarkup showAnswer;
    private final InlineKeyboardMarkup chooseCollection;

    @Autowired
    public ResponseExecutorImpl(SenseOfLanguageBotConfig senseOfLanguageBotConfig,
                                WordService wordService,
                                ProfileService profileService,
                                ImageService imageService,
                                @Qualifier(value = "start") ReplyKeyboardMarkup start,
                                @Qualifier(value = "loadWord") ReplyKeyboardMarkup loadWord,
                                @Qualifier(value = "showAnswer") ReplyKeyboardMarkup showAnswer,
                                @Qualifier(value = "chooseCollection") InlineKeyboardMarkup chooseCollection,
                                @Qualifier(value = "hat") InlineKeyboardMarkup hat) {
        this.solbc = senseOfLanguageBotConfig;
        this.wordService = wordService;
        this.profileService = profileService;
        this.imageService = imageService;
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

        InputFile photo;
        try {
            photo = imageService.htmlToImage(word.getEng());
        } catch (IOException e) {
            execute(new SendMessage()
            .setChatId(chatId)
            .setText("Word: " + word.getEng()));
            return;
        }

        SendPhoto message = new SendPhoto()
                .setChatId(chatId)
                .setPhoto(photo)
                .setReplyMarkup(loadWord);
        executePhoto(message);
    }

    @Override
    public void showByWordId(Update update, String currentWordId) {
        String chatId = getChatId(update);

        Word word = wordService.findById(currentWordId);

        InputFile photo;
        try {
            photo = imageService.htmlToImage(word.getUkr());
        } catch (IOException e) {
            execute(new SendMessage()
                    .setChatId(chatId)
                    .setText("Answer: " + word.getUkr()));
            return;
        }

        SendPhoto message = new SendPhoto()
                .setChatId(chatId)
                .setPhoto(photo)
                .setReplyMarkup(showAnswer);
        executePhoto(message);
    }

    @Override
    public void collectionFinishAndStatistic(Update update, CollectionType collectionType) {
        Integer progress;
        List<WordInfo> mostHardWordForUser;
        List<WordInfo> mostHardWordByFrequency;

        Profile profile = profileService.getProfile(update);



    }

    @Override
    public void showStatistic(Update update, CollectionType collectionType) {
        Integer progressInPercentage;
        List<WordInfo> mostHardWordForUser;
        List<WordInfo> mostHardWordByFrequency;

        Profile profile = profileService.getProfile(update);
        Collection collection = profile.getCollections().stream()
                .filter(c -> c.getCollectionType().equals(collectionType))
                .findFirst()
                .orElseThrow(() -> new ModelNotFoundException("Collection with " + collectionType.text() + " not found!"));

        Long totalLearnedWords = collection.getWords().stream()
                .filter(w -> w.getWordState().equals(WordState.KNOW))
                .count();

        mostHardWordForUser = collection.getWords().stream()
                .sorted(Comparator.comparing(WordInfo::getTimesToSolve))
                .limit(10)
                .collect(Collectors.toList());

        List<String> wordIds = collection.getWords().stream()
                .map(WordInfo::getWordId)
                .collect(Collectors.toList());

        List<Word> words = wordService.findAllByIdIn(wordIds);

        List<Word> wordsByFrequencyAsc = words.stream()
                .sorted(Comparator.comparing(Word::getFrequency))
                .limit(10)
                .collect(Collectors.toList());


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

    private void executePhoto(SendPhoto sendPhoto) {
        try {
            solbc.execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
