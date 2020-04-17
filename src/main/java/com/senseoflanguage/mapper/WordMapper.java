package com.senseoflanguage.mapper;

import com.senseoflanguage.dto.request.WordRequest;
import com.senseoflanguage.dto.response.WordResponse;
import com.senseoflanguage.model.Word;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WordMapper {

    private DozerBeanMapper mapper;

    public WordMapper() {
    }

    @Autowired
    public WordMapper(DozerBeanMapper mapper) {
        this.mapper = mapper;
    }


    public Word requestToEntity(WordRequest dto) {
        return dto == null ? null : mapper.map(dto, Word.class);
    }

    public Word requestToEntity(WordRequest dto, Word model) {
        if (dto == null && model == null) {
            return new Word();
        } else if (dto == null) {
            return model;
        } else if (model == null) {
            return mapper.map(dto, Word.class);
        } else {
            mapper.map(dto, model);
            return model;
        }
    }

    public WordResponse toDtoResponse(Word model) {
        return model == null ? null : mapper.map(model, WordResponse.class);
    }

}
