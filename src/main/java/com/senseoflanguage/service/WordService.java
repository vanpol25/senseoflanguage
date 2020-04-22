package com.senseoflanguage.service;

import com.senseoflanguage.model.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WordService {

    Word save(Word word);

    Word delete(String id);

    Word delete(Word word);

    Word findById(String id);

    Page<Word> findAll(Pageable pageable);

    List<Word> saveAll(List<Word> requests);

    List<Word> deleteAllByBody(List<Word> requests);

    Word findByName(String name);
}
