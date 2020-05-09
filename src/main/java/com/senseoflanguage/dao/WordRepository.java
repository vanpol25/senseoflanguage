package com.senseoflanguage.dao;

import com.senseoflanguage.model.Word;
import com.senseoflanguage.model.enums.CollectionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface WordRepository extends MongoRepository<Word, String> {

    Optional<Word> findByEng(String name);

    List<Word> findAllByCollections(CollectionType collection);

    Page<Word> findByCollections(CollectionType collection, Pageable pageable);

    List<Word> findAllByCollectionsOrderByFrequencyDesc(CollectionType collection);

//    Word save(String json);

}
