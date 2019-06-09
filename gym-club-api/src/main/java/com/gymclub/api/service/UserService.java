package com.gymclub.api.service;


import com.gymclub.api.domain.UmUser;
import com.gymclub.api.domain.UserInfo;
import com.gymclub.api.dto.PwdChangeDTO;
import com.gymclub.api.dto.UserProfile;
import reactor.core.publisher.Mono;

/**
 * @author Xiaoming.
 * Created on 2019/03/29 18:21.
 */
public interface UserService {
    UserInfo getUserInfoByName(String username);

    Mono<UserInfo> updateProfile(UserProfile newProfile, String username);

    Mono<UmUser> changePassword(PwdChangeDTO data);
}
