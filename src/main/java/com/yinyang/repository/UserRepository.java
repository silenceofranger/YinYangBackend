package com.yinyang.repository;

import com.yinyang.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>, CustomUserRepository {
    // Custom repository methods if needed
}
