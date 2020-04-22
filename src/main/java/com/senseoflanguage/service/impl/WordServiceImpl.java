package com.senseoflanguage.service.impl;

import com.senseoflanguage.dao.WordRepository;
import com.senseoflanguage.exception.ModelNotFoundException;
import com.senseoflanguage.model.Word;
import com.senseoflanguage.service.WordDefinition;
import com.senseoflanguage.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordServiceImpl implements WordService {

    private WordRepository wordRepository;
    private WordDefinition wordDefinition;

    @Autowired
    public WordServiceImpl(WordRepository wordRepository,
                           WordDefinition wordDefinition) {
        this.wordRepository = wordRepository;
        this.wordDefinition = wordDefinition;
    }

    @Override
    public Word save(Word word) {
        return wordRepository.save(word);
    }

    @Override
    public List<Word> saveAll(List<Word> requests) {
        return wordRepository.saveAll(requests);
    }

    @Override
    public Word delete(String id) {
        Word findById = findById(id);

        wordRepository.delete(findById);
        return findById;
    }

    @Override
    public Word delete(Word word) {
        return delete(word.getId());
    }

    @Override
    @Deprecated
    public List<Word> deleteAllByBody(List<Word> requests) {
        List<String> wordsId = requests.stream().map(Word::getId).collect(Collectors.toList());
        Iterable<Word> allById = wordRepository.findAllById(wordsId);
        wordRepository.deleteAll(allById);
        return requests;
    }

    @Override
    public Word findById(String id) {
        return wordRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException(String.format("Could not find word with id = '%s'!", id)));
    }

    @Override
    public Word findByName(String name) {
        return wordRepository.findByEng(name)
                .orElseThrow(() -> new ModelNotFoundException(String.format("Could not find word with name = '%s'!", name)));
    }

    @Override
    public Page<Word> findAll(Pageable pageable) {
        return wordRepository.findAll(pageable);
    }

}
