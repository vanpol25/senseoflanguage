package com.senseoflanguage.mapper;

import com.senseoflanguage.model.Definition;
import com.senseoflanguage.model.words_api.DefinitionWordsApi;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefinitionMapper {

    private final DozerBeanMapper mapper;

    @Autowired
    public DefinitionMapper(DozerBeanMapper mapper) {
        this.mapper = mapper;
    }

    public Definition map(DefinitionWordsApi source) {
        return source == null ? null : mapper.map(source, Definition.class);
    }

}
