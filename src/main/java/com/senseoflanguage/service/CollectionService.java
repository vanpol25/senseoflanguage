package com.senseoflanguage.service;

import com.senseoflanguage.model.Collection;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CollectionService {
    Collection create(Collection collection);

    Collection update(Collection collection);

    void delete(String id);

    Optional<Collection> findById(String id);

    List<Collection> findAllById(List<String> collectionIds);
}
