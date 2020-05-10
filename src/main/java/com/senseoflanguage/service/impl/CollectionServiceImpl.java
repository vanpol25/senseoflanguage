package com.senseoflanguage.service.impl;

import com.senseoflanguage.dao.CollectionRepository;
import com.senseoflanguage.exception.ModelNotFoundException;
import com.senseoflanguage.model.Collection;
import com.senseoflanguage.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;

    @Autowired
    public CollectionServiceImpl(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    @Override
    public Collection create(Collection collection) {
        try {
            return collectionRepository.save(collection);
        } catch (StackOverflowError error) {
            error.printStackTrace();
            throw error;
        }
    }

    @Override
    public Collection update(Collection collection) {
        try {
            return collectionRepository.save(collection);
        } catch (StackOverflowError error) {
            error.printStackTrace();
            throw error;
        }
    }

    @Override
    public void delete(String id) {
        Collection collection = findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Collection not found with id=" + id));
        try {
            collectionRepository.delete(collection);
        } catch (StackOverflowError error) {
            error.printStackTrace();
            throw error;
        }
    }

    @Override
    public Optional<Collection> findById(String id) {
        try {
            return collectionRepository.findById(id);
        } catch (StackOverflowError error) {
            error.printStackTrace();
            throw error;
        }
    }

    @Override
    public List<Collection> find(Set<String> collectionIds) {
        List<Collection> collections = new ArrayList<>();
        try {
            collectionRepository.findAllById(collectionIds)
                    .forEach(collections::add);
        } catch (StackOverflowError error) {
            error.printStackTrace();
            throw error;
        }
        return collections;
    }

}
