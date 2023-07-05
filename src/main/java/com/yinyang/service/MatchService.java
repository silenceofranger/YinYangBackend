package com.yinyang.service;

import com.yinyang.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class MatchService {

    @Autowired UserService userService;
    public Boolean userLiked(String logInUserId, String likedUserId) {
        User logInUser = userService.getUserById(logInUserId);
        User likedUser = userService.getUserById(likedUserId);
        Set<String> logInUserLikedSet = logInUser.getLikedUserIds();
        Set<String> likedUserLikedSet = likedUser.getLikedUserIds();

        logInUserLikedSet.add(likedUserId);

        // IT IS A MATCH
        if(likedUserLikedSet.contains(logInUserId)){
            log.info("event=MatchHappened + likedUserId=" + likedUserId + " logInUserId=" + logInUserId);
            likedUser.getMatchedUserIds().add(logInUserId);
            logInUser.getMatchedUserIds().add(likedUserId);
            userService.saveUsers(List.of(logInUser, likedUser));
            return true;
        } else{
            userService.saveUser(likedUser);
            return false;
        }
    }
}
