package com.gymclub.api.controller;

import com.gymclub.api.dto.CommonRestResult;
import com.gymclub.api.dto.UserSignUpRequest;
import com.gymclub.api.service.UserSignUpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author Xiaoming.
 * Created on 2019/05/10 17:49.
 */
@Slf4j
@Component
public class UserSignUpHandler {
    @Autowired
    private UserSignUpService userSignUpService;

    public Mono<ServerResponse> signUp(ServerRequest request) {
        UserSignUpRequest param = request.bodyToMono(UserSignUpRequest.class).block();
        return ServerResponse.ok().body(Mono.just(userSignUpService.register(param)), CommonRestResult.class);
    }
}
