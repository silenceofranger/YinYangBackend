package com.yinyang.controller;


import com.yinyang.model.ImagesRequest;
import com.yinyang.model.User;
import com.yinyang.model.UserFilter;
import com.yinyang.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/v1")
@Slf4j
public class Controller {

    @Autowired
    UserService userService;

    @GetMapping("/user/{userId}")
    public User getUser(@PathVariable String userId){
        User user = userService.getUserById(userId);
        log.info("event=userFetchedById userId=" + userId);
        return user;
    }

    @GetMapping("/phone/{phone}")
    public User getUserByPhone(@PathVariable String phone){
        User user = userService.getUserByPhone(phone);
        log.info("event=userFetchedById userId=" + user.get_id());
        return user;
    }

    @PostMapping("/user")
    public User saveUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        log.info("event=userSaved user=" + user);
        return savedUser;
    }

    @PostMapping("/images")
    public Boolean saveUser(@RequestBody ImagesRequest imagesRequest){
        Boolean saved = userService.savePhotosDownloadUrl(imagesRequest);
        log.info("event=imagesUploaded user=" + imagesRequest.getUserId());
        return saved;
    }

    @PostMapping("/home")
    public List<User> home(@RequestBody UserFilter userFilter){
        String userId = userFilter.getUserId();
        User user = getUser(userId);
        Set<String> likedUserIds = user.getLikedUserIds();
        Set<String> dislikedUserIds = user.getDislikedUserIds();
        List<String> excludingUserIds = new ArrayList<>();
        excludingUserIds.add(userId);
        excludingUserIds.addAll(likedUserIds);
        excludingUserIds.addAll(dislikedUserIds);
        List<User> users = userService.getUsersExcluding(excludingUserIds);
        log.info("event=usersFetchedForUser userId={} totalUsersFetched={}", userId , users.size());
        return users;
    }
}
