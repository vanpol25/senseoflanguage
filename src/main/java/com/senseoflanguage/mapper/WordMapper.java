package com.senseoflanguage.mapper;

import com.senseoflanguage.dto.request.WordRequest;
import com.senseoflanguage.dto.response.WordResponse;
import com.senseoflanguage.model.Word;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WordMapper {

    private DozerBeanMapper mapper;

    public WordMapper() {
    }

    @Autowired
    public WordMapper(DozerBeanMapper mapper) {
        this.mapper = mapper;
    }


    public Word requestToModel(WordRequest dto) {
        return dto == null ? null : mapper.map(dto, Word.class);
    }

    public Word requestToModel(WordRequest dto, Word model) {
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

    public List<Word> requestsToModels(List<WordRequest> requests) {
        if (requests == null) {
            return Collections.emptyList();
        } else {
            return requests.stream()
                    .map(o -> mapper.map(o, Word.class))
                    .collect(Collectors.toList());
        }
    }

    public WordResponse modelToResponse(Word model) {
        return model == null ? null : mapper.map(model, WordResponse.class);
    }

    public List<WordResponse> modelsToResponses(List<Word> models) {
        if (models == null) {
            return Collections.emptyList();
        } else {
            return models.stream()
                    .map(o -> mapper.map(o, WordResponse.class))
                    .collect(Collectors.toList());
        }
    }

}
