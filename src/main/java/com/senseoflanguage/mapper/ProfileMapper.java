package com.senseoflanguage.mapper;

import com.senseoflanguage.dto.request.WordRequest;
import com.senseoflanguage.dto.response.PageResponse;
import com.senseoflanguage.dto.response.WordResponse;
import com.senseoflanguage.model.Definition;
import com.senseoflanguage.model.Profile;
import com.senseoflanguage.model.Word;
import com.senseoflanguage.model.my_db.MainText;
import com.senseoflanguage.model.my_db.Meaning;
import com.senseoflanguage.model.words_api.DefinitionWordsApi;
import com.senseoflanguage.model.words_api.WordWordsApi;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ProfileMapper {

    private final DozerBeanMapper mapper;

    @Autowired
    public ProfileMapper(DozerBeanMapper mapper) {
        this.mapper = mapper;
    }

    public Profile map(User source) {
        return source == null ? null : mapper.map(source, Profile.class);
    }

}
