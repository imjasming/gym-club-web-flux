package com.gymclub.api.router;

import com.gymclub.api.controller.GymHandler;
import com.gymclub.api.controller.HateoasHandler;
import com.gymclub.api.controller.TrainerHandler;
import com.gymclub.api.controller.UserInfoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author Xiaoming.
 * Created on 2019/06/08 23:43.
 */
@Configuration
public class Routers {
    @Bean
    public RouterFunction<ServerResponse> gymRoute(GymHandler handler) {
        return RouterFunctions.route().GET("/gyms", handler::getGymList).build();
    }

    @Bean
    public RouterFunction<ServerResponse> hateoasRoute(HateoasHandler handler) {
        return RouterFunctions.route().GET("/greeting", handler::greeting).build();
    }

    @Bean
    public RouterFunction<?> trainerRoute(TrainerHandler handler) {
        return RouterFunctions.route()
                .path("/user", builder -> builder
                        .GET("/{username}/trainers", handler::getUserTrainerList)
                        .POST("/{username}/trainer", handler::addUserSTrainer))
                .GET("/trainers", handler::getTrainerList)
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userInfoRoute(UserInfoHandler handler) {
        return RouterFunctions.route()
                .path("/user/{username}", builder -> builder
                        .GET("/info", handler::getUserInfo)
                        .PUT("/profile", handler::updateProfile)
                        .PUT("/password", handler::resetPassword))
                .build();
    }
}
