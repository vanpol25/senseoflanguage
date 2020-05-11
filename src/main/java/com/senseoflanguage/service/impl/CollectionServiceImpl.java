package com.senseoflanguage.service.impl;

import com.senseoflanguage.dao.CollectionRepository;
import com.senseoflanguage.exception.ModelNotFoundException;
import com.senseoflanguage.model.Collection;
import com.senseoflanguage.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;

    @Autowired
    public CollectionServiceImpl(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    @Override
    public Collection create(Collection collection) {
        return collectionRepository.save(collection);
    }

    @Override
    public Collection update(Collection collection) {
        return collectionRepository.save(collection);
    }

    @Override
    public void delete(String id) {
        Collection collection = findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Collection not found with id=" + id));
        collectionRepository.delete(collection);
    }

    @Override
    public Optional<Collection> findById(String id) {
        return collectionRepository.findById(id);
    }

    @Override
    public List<Collection> findAllById(List<String> collectionIds) {
        return collectionRepository.findAllByIdIn(collectionIds);
    }

}
