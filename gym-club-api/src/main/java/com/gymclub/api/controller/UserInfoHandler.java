package com.gymclub.api.controller;

import com.gymclub.api.domain.UmUser;
import com.gymclub.api.domain.UserInfo;
import com.gymclub.api.dto.PwdChangeDTO;
import com.gymclub.api.dto.UserProfile;
import com.gymclub.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author Xiaoming.
 * Created on 2019/04/29 18:18.
 */
@Api(tags = "UserInfoController")
@Component
public class UserInfoHandler {
    @Autowired
    private UserService userService;

    @ApiOperation("get user's info")
    public Mono<ServerResponse> getUserInfo(ServerRequest request) {
        final String username = request.pathVariable("username");
        UserInfo userInfo = userService.getUserInfoByName(username);
        return ServerResponse.ok().body(Mono.just(userInfo), UserInfo.class);
    }

    @ApiOperation("update user's profile")
    public Mono<ServerResponse> updateProfile(ServerRequest request) {
        final String username = request.pathVariable("username");
        UserProfile newProfile = request.bodyToMono(UserProfile.class).block();
        Objects.requireNonNull(newProfile).setUsername(username);
        return ServerResponse.ok().body(userService.updateProfile(newProfile, username), UserInfo.class);
    }

    @ApiOperation("update user's password")
    public Mono<ServerResponse> resetPassword(ServerRequest request) {
        return ServerResponse.ok().body(userService.changePassword(request.bodyToMono(PwdChangeDTO.class).block()), UmUser.class);
    }
}
