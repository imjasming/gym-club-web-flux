package com.gymclub.api.service;


import com.gymclub.api.domain.primary.UmUser;
import com.gymclub.api.domain.secondary.UserInfo;
import com.gymclub.api.dto.UserProfile;

/**
 * @author Xiaoming.
 * Created on 2019/03/29 18:21.
 */
public interface UserService {
    UmUser getUserByName(String username);

    UserInfo getUserInfoByName(String username);

    UserInfo updateProfile(UserProfile newProfile, String username);

    //changePassword
    UmUser changePassword(String username, String oldPassword, String password);
}
