package com.senseoflanguage.service.impl;

import com.senseoflanguage.dao.ProfileRepository;
import com.senseoflanguage.exception.ModelNotFoundException;
import com.senseoflanguage.model.Profile;
import com.senseoflanguage.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile create(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public synchronized Profile update(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Optional<Profile> findById(String id) {
        return profileRepository.findById(id);
    }

    @Override
    public Profile getProfile(Update update) {
        String profileId = getProfileId(update);
        return findById(profileId)
                .orElseThrow(() -> new ModelNotFoundException("Profile not found!"));
    }

    @Override
    public String getProfileId(Update update) {
        String profileId;
        if (update.getMessage() != null) {
            profileId = update.getMessage().getFrom().getId().toString();
        } else {
            profileId = update.getCallbackQuery().getFrom().getId().toString();
        }
        return profileId;
    }

}
