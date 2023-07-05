package com.yinyang.service;

import com.yinyang.model.ImagesRequest;
import com.yinyang.model.User;
import com.yinyang.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        if(user.get_id() == null) {
            String userId = UUID.randomUUID().toString();
            user.set_id(userId);
        }
        User savedUser = userRepository.save(user);
        log.info("event=userSaved userSaved=" + user);
        return savedUser;
    }

    public List<User> saveUsers(List<User> users){
        List<User> usersSaved = userRepository.saveAll(users);
        log.info("event=usersSaved totalUsersSaved=" + users.size());
        return usersSaved;
    }

    public Boolean savePhotosDownloadUrl(ImagesRequest imagesRequest) {
        Boolean saved =  userRepository.savePhotosDownloadUrl(imagesRequest);
        log.info("event=userImagesUrlSaved userId=" + imagesRequest.getUserId() + " userImages=" + imagesRequest.getImagesUrlWithIndex().toString());
        return saved;
    }

    public User getUserById(String userId){
        return userRepository.findById(userId).orElse(User.builder().build());
    }

    public List<User> getUsersExcluding(List<String> userIds){
        return userRepository.findUsersExcluding(userIds);
    }

    public User getUserByPhone(String phone) {
        return userRepository.getUserByMobileNumber(phone);
    }

    public List<User> getUsersByIds(Set<String> userIds) {
        return userRepository.findAllById(userIds);
    }
}
