package com.senseoflanguage.dao;

import com.senseoflanguage.model.Word;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WordRepository extends MongoRepository<Word, String> {

    Optional<Word> findByEng(String name);

}
