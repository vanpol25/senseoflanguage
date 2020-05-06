package com.senseoflanguage.mapper;

import com.senseoflanguage.dto.request.WordRequest;
import com.senseoflanguage.dto.response.PageResponse;
import com.senseoflanguage.dto.response.WordResponse;
import com.senseoflanguage.model.Word;
import com.senseoflanguage.model.words_api.DefinitionWordsApi;
import com.senseoflanguage.model.words_api.WordWordsApi;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WordMapper {

    private final DozerBeanMapper mapper;
    private final DefinitionMapper definitionMapper;

    @Autowired
    public WordMapper(DozerBeanMapper mapper,
                      DefinitionMapper definitionMapper) {
        this.mapper = mapper;
        this.definitionMapper = definitionMapper;
    }

    public Word map(WordRequest source) {
        return source == null ? null : mapper.map(source, Word.class);
    }

    public Word map(WordRequest source, Word destination) {
        if (source == null && destination == null) {
            return null;
        } else if (source == null) {
            return destination;
        } else if (destination == null) {
            return mapper.map(source, Word.class);
        } else {
            mapper.map(source, destination);
            return destination;
        }
    }

    public Word map(WordWordsApi source, Word destination) {
        if (source == null && destination == null) {
            return null;
        } else if (source == null) {
            return destination;
        } else if (destination == null) {
            return null;
        } else {
            for (DefinitionWordsApi definition : source.getResults()) {
                destination.getResults().add(definitionMapper.map(definition));
            }
            destination.setFrequency(source.getFrequency());
            return destination;
        }
    }

    public List<Word> mapList(List<WordRequest> source) {
        if (source == null) {
            return Collections.emptyList();
        } else {
            return source.stream()
                    .map(o -> mapper.map(o, Word.class))
                    .collect(Collectors.toList());
        }
    }

    public WordResponse mapResponse(Word source) {
        return source == null ? null : mapper.map(source, WordResponse.class);
    }

    public List<WordResponse> mapListResponse(List<Word> source) {
        if (source == null) {
            return Collections.emptyList();
        } else {
            return source.stream()
                    .map(o -> mapper.map(o, WordResponse.class))
                    .collect(Collectors.toList());
        }
    }

    public PageResponse<WordResponse> mapPage(Page<Word> source) {
        if (source == null) {
            return new PageResponse<>();
        } else {
            return new PageResponse<>(
                    source.getTotalPages(),
                    source.getTotalElements(),
                    source.get()
                            .map(this::mapResponse)
                            .collect(Collectors.toList())
            );
        }
    }

}
