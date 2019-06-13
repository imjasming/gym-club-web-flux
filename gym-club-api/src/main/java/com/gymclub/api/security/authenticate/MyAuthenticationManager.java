package com.gymclub.api.security.authenticate;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author Xiaoming.
 * Created on 2019/06/11 15:10.
 */
@Component
public class MyAuthenticationManager implements ReactiveAuthenticationManager {
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        // JwtAuthenticationToken is my custom token.
        if (authentication instanceof JwtAuthenticationToken) {
            authentication.setAuthenticated(true);
        }
        return Mono.just(authentication);
    }
}
