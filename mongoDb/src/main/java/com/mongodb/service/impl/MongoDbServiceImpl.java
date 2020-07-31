package com.mongodb.service.impl;

import com.mongodb.dao.MongoDbDao;
import com.mongodb.dto.Comments;
import com.mongodb.service.MongoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service("mongoDbService")
public class MongoDbServiceImpl implements MongoDbService {

    @Autowired
    private MongoDbDao mongoDbDao;
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 新增
     * @param comments
     */
    @Override
    public void insert(Comments comments) {
        mongoDbDao.insert(comments);
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Comments> queryAll() {
        return mongoDbDao.findAll();
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Optional<Comments> queryById(String id) {
        return mongoDbDao.findById(id);
    }

    /**
     * 根据id删除
     * @param id
     */
    @Override
    public void deleteById(String id) {
        mongoDbDao.deleteById(id);
    }
}
