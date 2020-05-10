package com.senseoflanguage.service.impl;

import com.senseoflanguage.dao.ProfileRepository;
import com.senseoflanguage.model.Profile;
import com.senseoflanguage.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository,
                              MongoTemplate mongoTemplate) {
        this.profileRepository = profileRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Profile create(Profile profile) {
        profileRepository.save(profile);
        return profile;
    }

    @Override
    public synchronized Profile update(Profile profile) {
        return mongoTemplate.save(profile, "profiles");
    }

    @Override
    public Optional<Profile> findById(String id) {
        return profileRepository.findById(id);
    }
}
