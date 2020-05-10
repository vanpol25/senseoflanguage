package com.senseoflanguage.dao;

import com.senseoflanguage.model.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CollectionRepository extends MongoRepository<Collection, String> {

}
