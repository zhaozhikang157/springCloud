package com.mongodb.dao;

import com.mongodb.dto.Comments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
@Repository
public interface MongoDbDao extends MongoRepository<Comments,String> {


}
