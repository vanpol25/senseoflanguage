package com.senseoflanguage.service.impl;

import com.senseoflanguage.dao.ProfileRepository;
import com.senseoflanguage.model.Profile;
import com.senseoflanguage.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        try {
            return profileRepository.save(profile);
        } catch (StackOverflowError error) {
            error.printStackTrace();
            throw error;
        }
    }

    @Override
    public synchronized Profile update(Profile profile) {
        try {
            return profileRepository.save(profile);
        } catch (StackOverflowError error) {
            error.printStackTrace();
            throw error;
        }
    }

    @Override
    public Optional<Profile> findById(String id) {
        try {
            return profileRepository.findById(id);
        } catch (StackOverflowError error) {
            error.printStackTrace();
            throw error;
        }
    }

}
