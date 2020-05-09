package com.senseoflanguage.service.impl;

import com.senseoflanguage.dao.ProfileRepository;
import com.senseoflanguage.mapper.ProfileMapper;
import com.senseoflanguage.model.Profile;
import com.senseoflanguage.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository,
                              ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }

    @Override
    public Profile save(User user) {
        Profile profile = profileMapper.map(user);
        return profileRepository.save(profile);
    }

}
