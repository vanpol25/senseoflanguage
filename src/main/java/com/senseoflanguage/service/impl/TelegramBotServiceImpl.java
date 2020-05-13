package com.senseoflanguage.service.impl;

import com.senseoflanguage.mapper.ProfileMapper;
import com.senseoflanguage.model.Collection;
import com.senseoflanguage.model.Profile;
import com.senseoflanguage.model.Word;
import com.senseoflanguage.model.WordInfo;
import com.senseoflanguage.model.enums.CollectionType;
import com.senseoflanguage.model.enums.WordState;
import com.senseoflanguage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TelegramBotServiceImpl implements TelegramBotService {

    private final ProfileService profileService;
    private final WordService wordService;
    private final CollectionService collectionService;
    private final ProfileMapper profileMapper;
    private final ResponseExecutor responseExecutor;
    private final Random random;

    @Autowired
    public TelegramBotServiceImpl(ProfileService profileService,
                                  WordService wordService,
                                  CollectionService collectionService,
                                  ProfileMapper profileMapper,
                                  ResponseExecutor responseExecutor,
                                  @Qualifier("random") Random random) {
        this.profileService = profileService;
        this.wordService = wordService;
        this.collectionService = collectionService;
        this.profileMapper = profileMapper;
        this.responseExecutor = responseExecutor;
        this.random = random;
    }

    @Override
    public void start(Update update) {
        String profileId = profileService.getProfileId(update);

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
        Profile profile = profileService.getProfile(update);
        Optional<Collection> collection = profile.getCollections().stream()
                .filter(c -> c.getCollectionType().equals(collectionType))
                .findFirst();

        if (collection.isPresent()) {
            loadNextWord(collection.get(), profile, update, collectionType);
        } else {
            List<Word> wordsByCollection = wordService.findAllByCollections(collectionType);
            Set<String> wordsIdByCollection = wordsByCollection.stream()
                    .map(Word::getId)
                    .collect(Collectors.toSet());

            Collection newCollection = new Collection();
            newCollection.setCollectionType(collectionType);
            for (String wordId : wordsIdByCollection) {
                profile.setCurrentWordId(wordId);
                WordInfo wordInfo = new WordInfo();
                wordInfo.setWordId(wordId);
                wordInfo.setTimesToSolve(0);
                wordInfo.setWordState(WordState.NEVER_SAW);
                newCollection.getWords().add(wordInfo);
            }
            collectionService.create(newCollection);
            profile.getCollections().add(newCollection);
            loadNextWord(newCollection, profile, update, collectionType);
        }
    }

    private void loadNextWord(Collection collection, Profile profile, Update update, CollectionType collectionType) {
        List<WordInfo> wordInfosNotKnow = collection.getWords().stream()
                .filter(w -> !w.getWordState().equals(WordState.KNOW))
                .collect(Collectors.toList());

        if (!wordInfosNotKnow.isEmpty()) {
            int rand = random.nextInt(100);

            WordState wordState = (rand < 65) ? WordState.NEVER_SAW :
                    (rand > 65 && rand < 90) ? WordState.DO_NOT_KNOW : WordState.DO_NOT_FULL_KNOW;

            List<WordInfo> wordInfos = collection.getWords().stream()
                    .filter(w -> !w.getWordState().equals(wordState))
                    .collect(Collectors.toList());

            String wordId;
            if (!wordInfos.isEmpty()) {
                wordId = wordInfos.get(random.nextInt(wordInfos.size())).getWordId();
            } else {
                wordId = wordInfosNotKnow.get(random.nextInt(wordInfosNotKnow.size())).getWordId();
            }
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
        Profile profile = profileService.getProfile(update);
        String currentWordId = profile.getCurrentWordId();
        if (currentWordId != null) {
            responseExecutor.showByWordId(update, currentWordId);
        } else {
            chooseCollection(update);
        }
    }

    @Override
    public void chooseDifficult(Update update, WordState wordState) {
        Profile profile = profileService.getProfile(update);
        CollectionType currentCollectionType = profile.getCurrentCollection();
        String currentWordId = profile.getCurrentWordId();
        if (currentCollectionType == null || currentWordId == null) {
            chooseCollection(update);
            return;
        }

        Optional<Collection> collectionOptional = profile.getCollections().stream()
                .filter(c -> c.getCollectionType().equals(currentCollectionType))
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
            collectionService.update(collection);
        } else {
            loadCollection(update, currentCollectionType);
            return;
        }

        loadNextWord(collection, profile, update, currentCollectionType);
    }

    @Override
    public void showExamples(Update update) {
        Profile profile = profileService.getProfile(update);
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
        Profile profile = profileService.getProfile(update);
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
        Profile profile = profileService.getProfile(update);
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
        Profile profile = profileService.getProfile(update);
        CollectionType currentCollection = profile.getCurrentCollection();
        responseExecutor.showStatistic(update, currentCollection);
    }

}