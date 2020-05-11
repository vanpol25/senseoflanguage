package com.senseoflanguage.dao;

import com.senseoflanguage.model.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CollectionRepository extends MongoRepository<Collection, String> {

    List<Collection> findAllByIdIn(List<String> ids);

}
