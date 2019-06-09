package com.gymclub.api.controller;

import com.gymclub.api.domain.UmUser;
import com.gymclub.api.domain.UserInfo;
import com.gymclub.api.dto.PwdChangeDTO;
import com.gymclub.api.dto.UserProfile;
import com.gymclub.api.repository.UserRepository;
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
//@RequestMapping("/user")
public class UserInfoHandler {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @ApiOperation("get user's info")
    //@GetMapping("/{username}/info")
    public Mono<ServerResponse> getUserInfo(
            //@PathVariable("username") String username
            ServerRequest request
    ) {
        final String username = request.pathVariable("username");
        UserInfo userInfo = userService.getUserInfoByName(username);
        return ServerResponse.ok().body(Mono.just(userInfo), UserInfo.class);
    }

    @ApiOperation("update user's profile")
    //@PutMapping("/{username}/profile")
    public Mono<ServerResponse> updateProfile(
            //@PathVariable String username, @RequestBody UserProfile newProfile
            ServerRequest request
    ) {
        final String username = request.pathVariable("username");
        UserProfile newProfile = request.bodyToMono(UserProfile.class).block();
        Objects.requireNonNull(newProfile).setUsername(username);
        return ServerResponse.ok().body(userService.updateProfile(newProfile, username), UserInfo.class);
    }

    @ApiOperation("update user's password")
    //@PutMapping("/{username}/password")
    public Mono<ServerResponse> resetPassword(
            /*@RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword,
            @PathVariable("username") String username*/
            ServerRequest request
    ) {
        return ServerResponse.ok().body(userService.changePassword(request.bodyToMono(PwdChangeDTO.class).block()), UmUser.class);
    }
}
