package com.mongodb.service;

import com.mongodb.dto.Comments;

import java.util.List;
import java.util.Optional;

public interface MongoDbService {

    public void insert(Comments comments);

    public List<Comments> queryAll();

    public Optional<Comments> queryById(String id);

    public void deleteById(String id);
}
