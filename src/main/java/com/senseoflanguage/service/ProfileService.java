package com.senseoflanguage.service;

import com.senseoflanguage.model.Profile;
import org.telegram.telegrambots.meta.api.objects.User;

public interface ProfileService {
    Profile save(User user);
}
