package com.yinyang.repository.impl;

import com.yinyang.model.ImagesRequest;
import com.yinyang.model.User;
import com.yinyang.repository.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

public class CustomUserRepositoryImpl implements CustomUserRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<User> findUsersExcluding(List<String> userIds) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").not().in(userIds));
        return mongoTemplate.find(query, User.class);
    }

    @Override
    public User getUserByMobileNumber(String mobileNumber) {
        Query query = new Query();
        query.addCriteria(Criteria.where("phone").is(mobileNumber));
        return mongoTemplate.find(query, User.class).stream().findFirst().orElse(User.builder().build());
    }

    @Override
    public Boolean savePhotosDownloadUrl(ImagesRequest imagesDownloadUrl) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(imagesDownloadUrl.getUserId()));
        Update update = new Update();
        update.set("imagesUrlWithIndex", imagesDownloadUrl.getImagesUrlWithIndex());
        return mongoTemplate.updateMulti(query, update, User.class).wasAcknowledged();
    }


}
