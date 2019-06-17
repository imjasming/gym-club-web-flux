package com.gymclub.api.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xiaoming.
 * Created on 2019/06/11 00:16.
 */
@Component
public class LoginHandler {
/*    public Mono<MyUserDetails> login(ServerWebExchange exchange) {

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
    }*/

    /**
     * response OAuth clients(is only github now)
     * @param request
     * @return a client list
     */
    public Mono<ServerResponse> getOAuthClient(ServerRequest request) {
        Map client = new HashMap();
        client.put("clientName", "Auth with Github");
        client.put("clientUrl", "https://github.com/login/oauth/authorize?client_id=de87e995aa6c1c726646&state=github");

        return ServerResponse.ok().body(Mono.just(Collections.singletonList(client)), List.class);
    }
}
