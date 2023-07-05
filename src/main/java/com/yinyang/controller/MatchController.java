package com.yinyang.controller;


import com.yinyang.model.ImagesRequest;
import com.yinyang.model.User;
import com.yinyang.model.UserFilter;
import com.yinyang.service.MatchService;
import com.yinyang.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@Slf4j
public class MatchController {

    @Autowired
    MatchService matchService;

    @GetMapping("match")
    public List<User> matches(@RequestParam("logInUserId") String logInUserId){
        return matchService.getMatchedUsersByUserId(logInUserId);
    }

    @PatchMapping("/like")
    public Boolean likeUser(@RequestParam("logInUserId") String logInUserId,
                            @RequestParam("likedUserId") String likedUserId){
        return matchService.userLiked(logInUserId, likedUserId);
    }

    @PatchMapping("/dislike")
    public Boolean dislikedUser(@RequestParam("logInUserId") String logInUserId,
                            @RequestParam("likedUserId") String dislikedUserId){
        return matchService.userDisliked(logInUserId, dislikedUserId);
    }
}
