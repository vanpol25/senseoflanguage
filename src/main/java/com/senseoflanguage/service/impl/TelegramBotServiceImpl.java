package com.senseoflanguage.service.impl;

import com.senseoflanguage.config.SenseOfLanguageBotConfig;
import com.senseoflanguage.dao.ProfileRepository;
import com.senseoflanguage.mapper.ProfileMapper;
import com.senseoflanguage.model.Profile;
import com.senseoflanguage.service.ProfileService;
import com.senseoflanguage.service.TelegramBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class TelegramBotServiceImpl implements TelegramBotService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final ProfileService profileService;
    private final SenseOfLanguageBotConfig senseOfLanguageBotConfig;

    @Autowired
    public TelegramBotServiceImpl(ProfileRepository profileRepository,
                                  ProfileMapper profileMapper,
                                  ProfileService profileService, SenseOfLanguageBotConfig senseOfLanguageBotConfig) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
        this.profileService = profileService;
        this.senseOfLanguageBotConfig = senseOfLanguageBotConfig;
    }

    @Override
    public void onCommandReceived(Update update) {
        User user = update.getMessage().getFrom();
        Profile profile = profileRepository.findById(user.getId().toString())
                .orElse(profileService.save(user));



    }

    @Override
    public void onMessageReceived(Update update) {

    }

    private SendMessage parseMessage(Update update) {
        User user = update.getMessage().getFrom();
        Profile profile = profileRepository.findById(user.getId().toString())
                .orElse(profileService.save(user));
        logicMessage(update);

        return null;
    }

    private void logicMessage(Update update) {
        if (update.getCallbackQuery().getData().equals("/start")) {

        }
    }

    private SendMessage commandQuery(Update update) {

        return null;
    }

}