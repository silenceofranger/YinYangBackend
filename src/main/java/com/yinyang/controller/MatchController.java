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

    @PatchMapping("/like")
    public Boolean likeUser(@RequestParam("logInUserId") String logInUserId,
                            @RequestParam("likedUserId") String likedUserId){
        Boolean isMatch = matchService.userLiked(logInUserId, likedUserId);
        log.info("event=userFetchedById userId=" + userId);return user;
    }
}
