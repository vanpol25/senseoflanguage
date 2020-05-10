package com.senseoflanguage.service.impl;

import com.senseoflanguage.exception.ModelNotFoundException;
import com.senseoflanguage.mapper.ProfileMapper;
import com.senseoflanguage.model.Collection;
import com.senseoflanguage.model.Profile;
import com.senseoflanguage.model.Word;
import com.senseoflanguage.model.WordInfo;
import com.senseoflanguage.model.enums.CollectionType;
import com.senseoflanguage.model.enums.WordState;
import com.senseoflanguage.service.ProfileService;
import com.senseoflanguage.service.ResponseExecutor;
import com.senseoflanguage.service.TelegramBotService;
import com.senseoflanguage.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TelegramBotServiceImpl implements TelegramBotService {

    private final ProfileService profileService;
    private final WordService wordService;
    private final ProfileMapper profileMapper;
    private final ResponseExecutor responseExecutor;

    @Autowired
    public TelegramBotServiceImpl(ProfileService profileService,
                                  WordService wordService,
                                  ProfileMapper profileMapper,
                                  ResponseExecutor responseExecutor) {
        this.profileService = profileService;
        this.wordService = wordService;
        this.profileMapper = profileMapper;
        this.responseExecutor = responseExecutor;
    }

    @Override
    public void start(Update update) {
        String profileId = getProfileId(update);

        Optional<Profile> optionalProfile = profileService.findById(profileId);
        if (optionalProfile.isPresent()) {
            responseExecutor.startPresentProfile(update);
        } else {
            Profile profile = optionalProfile
                    .orElse(profileService.create(
                            profileMapper.map(update.getMessage().getFrom())));

            responseExecutor.startNewProfile(update);
            chooseCollection(update);
            profileService.create(profile);
        }
    }

    @Override
    public void chooseCollection(Update update) {
        responseExecutor.chooseCollection(update);
    }

    @Override
    public void loadCollection(Update update, CollectionType collectionType) {
        Profile profile = getProfile(update);

        Optional<Collection> collectionOptional = profile.getCollections().stream()
                .filter(c -> c.getName().equals(collectionType))
                .findFirst();

        if (collectionOptional.isPresent()) {
            loadNextWord(collectionOptional.get(), profile, update, collectionType);
        } else {
            List<Word> wordsByCollection = wordService.findAllByCollections(collectionType);
            Set<String> wordsIdByCollection = wordsByCollection.stream()
                    .map(Word::getId)
                    .collect(Collectors.toSet());

            Collection newCollection = new Collection() {{
                setName(collectionType);
                for (String wordId : wordsIdByCollection) {
                    profile.setCurrentWordId(wordId);
                    getWords().add(new WordInfo() {{
                        setWordId(wordId);
                        setTimesToSolve(0);
                        setWordState(WordState.DO_NOT_KNOW);
                    }});
                }
            }};
            profile.getCollections().add(newCollection);
            loadNextWord(newCollection, profile, update, collectionType);
        }
    }

    private void loadNextWord(Collection collection, Profile profile, Update update, CollectionType collectionType) {
        Optional<WordInfo> wordInfoOptional = collection.getWords().stream()
                .filter(w -> !w.getWordState().equals(WordState.KNOW))
                .findFirst();
        if (wordInfoOptional.isPresent()) {
            String wordId = wordInfoOptional.get().getWordId();
            responseExecutor.loadWord(update, wordId);
            profile.setCurrentCollection(collectionType);
            profile.setCurrentWordId(wordId);
        } else {
            responseExecutor.collectionFinishAndStatistic(update, collectionType);
            profile.setCurrentCollection(null);
            profile.setCurrentWordId(null);
        }
        profileService.update(profile);
    }

    @Override
    public void show(Update update) {
        Profile profile = getProfile(update);
        String currentWordId = profile.getCurrentWordId();
        if (currentWordId != null) {
            responseExecutor.showByWordId(update, currentWordId);
        } else {
            chooseCollection(update);
        }
    }

    @Override
    public void chooseDifficult(Update update, WordState wordState) {
        Profile profile = getProfile(update);
        CollectionType currentCollectionType = profile.getCurrentCollection();
        String currentWordId = profile.getCurrentWordId();
        if (currentCollectionType == null || currentWordId == null) {
            chooseCollection(update);
            return;
        }

        Optional<Collection> collectionOptional = profile.getCollections().stream()
                .filter(c -> c.getName().equals(currentCollectionType))
                .findFirst();
        Collection collection;
        if (collectionOptional.isPresent()) {
            collection = collectionOptional.get();
        } else {
            chooseCollection(update);
            return;
        }

        Optional<WordInfo> wordInfoOptional = collection.getWords().stream()
                .filter(w -> w.getWordId().equals(currentWordId))
                .findFirst();

        if (wordInfoOptional.isPresent()) {
            WordInfo wordInfo = wordInfoOptional.get();
            wordInfo.setTimesToSolve(Optional.of(wordInfo.getTimesToSolve()).orElse(0) + 1);
            wordInfo.setWordState(wordState);
            profileService.update(profile);
        } else {
            loadCollection(update, currentCollectionType);
            return;
        }

        loadNextWord(collection, profile, update, currentCollectionType);
    }

    @Override
    public void showExamples(Update update) {
        Profile profile = getProfile(update);
        String currentWordId = profile.getCurrentWordId();
        if (currentWordId != null) {
            Word word = wordService.findById(currentWordId);
            responseExecutor.showExamples(update, word);
        } else {
            chooseCollection(update);
        }
    }

    @Override
    public void showDetails(Update update) {
        Profile profile = getProfile(update);
        String currentWordId = profile.getCurrentWordId();
        if (currentWordId != null) {
            Word word = wordService.findById(currentWordId);
            responseExecutor.showDetails(update, word);
        } else {
            chooseCollection(update);
        }
    }

    @Override
    public void showAllInfo(Update update) {
        Profile profile = getProfile(update);
        String currentWordId = profile.getCurrentWordId();
        if (currentWordId != null) {
            Word word = wordService.findById(currentWordId);
            responseExecutor.showAllInfo(update, word);
        } else {
            chooseCollection(update);
        }
    }

    @Override
    public void showStatistic(Update update) {
        Profile profile = getProfile(update);
        CollectionType currentCollection = profile.getCurrentCollection();
        if (currentCollection != null) {
            Optional<Collection> collectionOptional = profile.getCollections().stream()
                    .filter(c -> c.getName().equals(currentCollection))
                    .findFirst();
            if (collectionOptional.isPresent()) {
                Collection collection = collectionOptional.get();
                responseExecutor.showStatistic(update, collection);
            } else {
                loadCollection(update, currentCollection);
            }
        } else {
            chooseCollection(update);
        }
    }

    private Profile getProfile(Update update) {
        String profileId = getProfileId(update);

        Optional<Profile> profileOptional = profileService.findById(profileId);

        if (profileOptional.isPresent()) {
            return profileOptional.get();
        } else {
            start(update);
            throw new ModelNotFoundException("Profile not found!");
        }
    }

    private String getProfileId(Update update) {
        String profileId;
        if (update.getMessage() != null) {
            profileId = update.getMessage().getFrom().getId().toString();
        } else {
            profileId = update.getCallbackQuery().getFrom().getId().toString();
        }
        return profileId;
    }

}