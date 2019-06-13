package com.gymclub.api.controller;

import com.gymclub.api.security.bo.MyUserDetails;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Xiaoming.
 * Created on 2019/06/11 00:16.
 */
@Component
public class LoginHandler {
    public Mono<MyUserDetails> login(ServerWebExchange exchange) {

        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .cast(MyUserDetails.class)
                .doOnNext(userDetails -> {
                    addTokenHeader(exchange.getResponse(), userDetails); // your job to code it the way you want
                });
    }

    private Mono<UserDetails> addTokenHeader(ServerHttpResponse response, MyUserDetails userDetails) {

        return null;
    }
}
