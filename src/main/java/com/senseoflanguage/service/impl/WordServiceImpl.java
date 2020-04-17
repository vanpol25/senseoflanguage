package com.senseoflanguage.service.impl;

import com.senseoflanguage.dao.WordRepository;
import com.senseoflanguage.exception.ModelNotFoundException;
import com.senseoflanguage.model.Word;
import com.senseoflanguage.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordServiceImpl implements WordService {

    private WordRepository wordRepository;

    @Autowired
    public WordServiceImpl(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public Word create(Word word) {
        return wordRepository.save(word);
    }

    @Override
    public Word update(Word word) {
        return wordRepository.save(word);
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
    public List<Word> createList(List<Word> words) {
        return wordRepository.saveAll(words);
    }

    @Override
    public List<Word> updateList(List<Word> words) {
        return wordRepository.saveAll(words);
    }

    @Override
    @Deprecated
    public List<Word> deleteList(List<Word> words) {
        List<String> wordsId = words.stream().map(Word::getId).collect(Collectors.toList());
        Iterable<Word> allById = wordRepository.findAllById(wordsId);
        wordRepository.deleteAll(allById);
        return words;
    }

    @Override
    public Word findById(String id) {
        return wordRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException(String.format("Could not find word with id = '%s'!", id)));
    }

}
