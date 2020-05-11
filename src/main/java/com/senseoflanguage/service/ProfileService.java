package com.senseoflanguage.service;

import com.senseoflanguage.model.Profile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Optional;

public interface ProfileService {

    Profile create(Profile profile);

    Profile update(Profile profile);

    Optional<Profile> findById(String id);

    Profile getProfile(Update update);

    String getProfileId(Update update);
}
