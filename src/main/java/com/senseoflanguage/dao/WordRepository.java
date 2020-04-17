package com.senseoflanguage.dao;

import com.senseoflanguage.model.Word;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WordRepository extends MongoRepository<Word, String> {

}
