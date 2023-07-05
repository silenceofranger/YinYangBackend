package com.yinyang.repository;

import com.yinyang.model.ImagesRequest;
import com.yinyang.model.User;

import java.util.List;

public interface CustomUserRepository {

    // Custom query method declaration(s)
    List<User> findUsersExcluding(List<String> userIds);

    User getUserByMobileNumber(String mobileNumber);

    Boolean savePhotosDownloadUrl(ImagesRequest imagesRequest);
}
