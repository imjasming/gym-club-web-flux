/*
package com.gymclub.api.router;

import com.gymclub.api.controller.UserInfoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

*/
/**
 * @author Xiaoming.
 * Created on 2019/06/09 21:26.
 *//*

@Configuration
public class UserInfoRouter {
    @Bean
    public RouterFunction<ServerResponse> route(UserInfoHandler handler) {
        return RouterFunctions.route()
                .path("/user/{username}", builder -> builder
                        .GET("/info", handler::getUserInfo)
                        .PUT("/profile", handler::updateProfile)
                        .PUT("/password", handler::resetPassword))
                .build();
    }
}
*/
